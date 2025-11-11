package com.viatik.service;

import com.viatik.entity.PlanillaGasto;
import com.viatik.entity.PlanillaGasto.EstadoPlanilla;
import com.viatik.entity.PlanillaGasto.TipoRegistro;
import com.viatik.repository.PlanillaGastoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AprobacionService {

    private final PlanillaGastoRepository planillaGastoRepository;
    // ... PerfilService para saber quién es el Aprobador de Perfil y Financiero

    public AprobacionService(PlanillaGastoRepository planillaGastoRepository) {
        this.planillaGastoRepository = planillaGastoRepository;
    }

    public List<PlanillaGasto> findPlanillasPendientesPorRol(String rol) {
        // Lógica para filtrar por estado:
        if ("APROBADOR".equals(rol)) {
            return planillaGastoRepository.findByTipoRegistroAndEstadoPlanilla(TipoRegistro.PLANILLA, EstadoPlanilla.EN_PROCESO);
        }
        if ("CONTABLE".equals(rol)) {
            // Contable revisa después de la aprobación de perfil
            return planillaGastoRepository.findByTipoRegistroAndEstadoPlanilla(TipoRegistro.PLANILLA, EstadoPlanilla.EN_PROCESO); // Mismo estado si AprobadorPerfil y Contable lo ven. Se necesita un campo para diferenciar.
        }
        if ("FINANCIERO".equals(rol)) {
            return planillaGastoRepository.findByTipoRegistroAndEstadoPlanilla(TipoRegistro.PLANILLA, EstadoPlanilla.REVISADO);
        }
        return List.of();
    }
    
    // --- Flujo Aprobador de Perfil ---
    public PlanillaGasto aprobarPorPerfil(Integer idPlanilla) {
        PlanillaGasto planilla = planillaGastoRepository.findByIdRegistroAndTipoRegistro(idPlanilla, TipoRegistro.PLANILLA);
        // **VALIDACIÓN DE ROL y PERFIL**
        
        planilla.setEstadoPlanilla(EstadoPlanilla.EN_PROCESO); // Pasa al contable
        return planillaGastoRepository.save(planilla);
    }
    
    // --- Flujo Contable ---
    public PlanillaGasto marcarGastoRevisado(Integer idGasto, boolean revisado) {
        PlanillaGasto gastoItem = planillaGastoRepository.findByIdRegistroAndTipoRegistro(idGasto, TipoRegistro.GASTO);
        if (gastoItem == null) throw new RuntimeException("Gasto item no encontrado.");
        
        gastoItem.setEstadoRevisado(revisado);
        return planillaGastoRepository.save(gastoItem);
    }

    public PlanillaGasto guardarRevisionPlanilla(Integer idPlanilla) {
        PlanillaGasto planilla = planillaGastoRepository.findByIdRegistroAndTipoRegistro(idPlanilla, TipoRegistro.PLANILLA);
        
        List<PlanillaGasto> gastos = planillaGastoRepository.findByIdPlanillaPadre(idPlanilla);
        
        // **VALIDACIÓN:** Chequear si TODOS los gastos en 'gastos' tienen estadoRevisado = TRUE
        boolean todosRevisados = gastos.stream().allMatch(PlanillaGasto::getEstadoRevisado);

        if (todosRevisados) {
            planilla.setEstadoPlanilla(EstadoPlanilla.REVISADO); // Pasa al Financiero
        } else {
            throw new RuntimeException("Aún quedan gastos por revisar.");
        }
        return planillaGastoRepository.save(planilla);
    }
    
    // --- Flujo Financiero ---
    public PlanillaGasto aprobarPorFinanciero(Integer idPlanilla) {
        PlanillaGasto planilla = planillaGastoRepository.findByIdRegistroAndTipoRegistro(idPlanilla, TipoRegistro.PLANILLA);
        // **VALIDACIÓN DE ROL**
        
        planilla.setEstadoPlanilla(EstadoPlanilla.APROBADO);
        // Aquí se actualiza el saldo del Fondo (descontando el total de la planilla)
        
        return planillaGastoRepository.save(planilla);
    }
    
    // --- Rechazo (Común) ---
    public PlanillaGasto rechazarPlanilla(Integer idPlanilla, String comentario) {
        PlanillaGasto planilla = planillaGastoRepository.findByIdRegistroAndTipoRegistro(idPlanilla, TipoRegistro.PLANILLA);
        
        planilla.setEstadoPlanilla(EstadoPlanilla.RECHAZADO);
        // El comentario de rechazo se puede añadir a una columna o log.
        // Se puede reutilizar el campo comentario_rechazo del Fondo si aplica.
        
        return planillaGastoRepository.save(planilla);
    }
}