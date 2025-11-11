package com.viatik.service;

import com.viatik.dto.GastoCreacionBaseDTO;
import com.viatik.dto.PlanillaCreacionDTO;
import com.viatik.entity.Fondo;
import com.viatik.entity.PlanillaGasto;
import com.viatik.entity.PlanillaGasto.EstadoPlanilla;
import com.viatik.entity.PlanillaGasto.TipoRegistro;
import com.viatik.repository.FondoRepository;
import com.viatik.repository.PlanillaGastoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanillaService {

    private final PlanillaGastoRepository planillaGastoRepository;
    private final FondoRepository fondoRepository;
    private final JsonService jsonService;
    // ... CatalogoService para obtener límites de movilidad

    public PlanillaService(PlanillaGastoRepository planillaGastoRepository, FondoRepository fondoRepository, JsonService jsonService) {
        this.planillaGastoRepository = planillaGastoRepository;
        this.fondoRepository = fondoRepository;
        this.jsonService = jsonService;
    }

    public PlanillaGasto crearPlanillaConGastos(PlanillaCreacionDTO planillaDto) {
        Fondo fondo = fondoRepository.findById(planillaDto.getIdFondo())
                                     .orElseThrow(() -> new RuntimeException("Fondo no existe o no está desembolsado."));
        
        // 1. Crear el registro principal (PLANILLA)
        PlanillaGasto planillaPrincipal = new PlanillaGasto();
        planillaPrincipal.setTipoRegistro(TipoRegistro.PLANILLA);
        planillaPrincipal.setIdFondo(fondo.getIdFondo());
        planillaPrincipal.setNombrePlanilla(planillaDto.getNombre());
        planillaPrincipal.setDescripcionPlanilla(planillaDto.getDescripcion());
        planillaPrincipal.setMoneda(planillaDto.getMoneda());
        planillaPrincipal.setIdPerfil(planillaDto.getIdPerfil());
        // El rendidor de perfil es APROBADOR, estado inicial EN_PROCESO.
        // Si el rendidor NO es APROBADOR, estado inicial es ABIERTO.
        planillaPrincipal.setEstadoPlanilla(EstadoPlanilla.ABIERTO); 
        planillaPrincipal.setFechaRegistro(LocalDateTime.now());
        
        PlanillaGasto savedPlanilla = planillaGastoRepository.save(planillaPrincipal);
        
        // 2. Agregar los gastos iniciales (ítems)
        if (planillaDto.getGastos() != null) {
            for (GastoCreacionBaseDTO gastoDto : planillaDto.getGastos()) {
                agregarGastoAPlanilla(savedPlanilla.getIdRegistro(), gastoDto);
            }
        }
        
        return savedPlanilla;
    }

    public PlanillaGasto agregarGastoAPlanilla(Integer idPlanillaPadre, GastoCreacionBaseDTO gastoDto) {
        // **VALIDACIONES CRÍTICAS:**
        // 1. Validar que la planilla padre exista y esté en estado ABIERTO/RECHAZADO.
        // 2. Validar que fecha_emision no sea mayor a fecha_contabilizacion.
        // 3. Validar el límite diario de movilidad (usando CatalogoService).
        // 4. Calcular precios brutos, IGV y Total antes de guardar en JSON.

        PlanillaGasto gastoItem = new PlanillaGasto();
        gastoItem.setTipoRegistro(TipoRegistro.GASTO);
        gastoItem.setIdPlanillaPadre(idPlanillaPadre);
        
        // Mapeo de campos comunes: rucProveedor, serieDocumento, fechas...
        gastoItem.setRucProveedor(gastoDto.getRucProveedor());
        // ... (otros campos)
        
        // Mapeo del detalle JSON
        gastoItem.setDetalleGasto(gastoDto.getDetalle());

        // Asegurar que idFondo, moneda e idPerfil se copien de la planilla padre
        PlanillaGasto planillaPadre = planillaGastoRepository.findById(idPlanillaPadre).orElseThrow();
        gastoItem.setIdFondo(planillaPadre.getIdFondo());
        gastoItem.setMoneda(planillaPadre.getMoneda());
        gastoItem.setIdPerfil(planillaPadre.getIdPerfil());

        return planillaGastoRepository.save(gastoItem);
    }
    
    public PlanillaGasto enviarParaAprobacion(Integer idPlanilla) {
        PlanillaGasto planilla = planillaGastoRepository.findById(idPlanilla)
                                                     .orElseThrow(() -> new RuntimeException("Planilla no encontrada"));
        
        // La lógica debe determinar si el rendidor es el APROBADOR.
        // Si APROBADOR_PERFIL != RENDIDOR, estado = EN_PROCESO (para Aprobador).
        // Si APROBADOR_PERFIL == RENDIDOR, estado = EN_PROCESO (para Contable).
        
        planilla.setEstadoPlanilla(EstadoPlanilla.EN_PROCESO);
        return planillaGastoRepository.save(planilla);
    }
    
    public List<PlanillaGasto> findPlanillasByRendidor(Integer idRendidor) {
        // Implementación con Query o lógica para filtrar por idFondo, que a su vez se relaciona con idRendidor
        return planillaGastoRepository.findAll(); // TEMPORAL
    }
}