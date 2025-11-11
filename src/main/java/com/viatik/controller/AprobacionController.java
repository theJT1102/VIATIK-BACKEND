package com.viatik.controller;

import com.viatik.dto.TransaccionDTO;
import com.viatik.entity.Fondo;
import com.viatik.entity.PlanillaGasto;
import com.viatik.service.AprobacionService;
import com.viatik.service.FondoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aprobacion")
public class AprobacionController {

    private final AprobacionService aprobacionService;
    private final FondoService fondoService;

    public AprobacionController(AprobacionService aprobacionService, FondoService fondoService) {
        this.aprobacionService = aprobacionService;
        this.fondoService = fondoService;
    }

    // --- Vistas de Flujo (Común para Aprobador, Contable, Financiero) ---

    @GetMapping("/pendientes")
    // Se requiere lógica para determinar qué planillas ve el usuario según su rol y perfil.
    // EJ: Un Contable ve planillas en estado EN_PROCESO. Un Financiero ve planillas en estado REVISADO.
    public ResponseEntity<List<PlanillaGasto>> getPlanillasPendientesRevision() {
        // Lógica compleja que usa el rol del usuario autenticado para filtrar
        String rolUsuario = "CONTABLE"; // EJEMPLO: Extraer del token
        List<PlanillaGasto> pendientes = aprobacionService.findPlanillasPendientesPorRol(rolUsuario);
        return ResponseEntity.ok(pendientes);
    }

    // --- Acciones de Aprobador de Perfil ---

    @PutMapping("/perfil/aprobar/{idPlanilla}")
    // Solo si el rol es APROBADOR y la planilla está en EN_PROCESO
    public ResponseEntity<PlanillaGasto> aprobarPerfil(@PathVariable Integer idPlanilla) {
        PlanillaGasto planilla = aprobacionService.aprobarPorPerfil(idPlanilla);
        return ResponseEntity.ok(planilla); // Estado cambia a EN_PROCESO (para Contable)
    }

    // --- Acciones de Contable ---

    @PutMapping("/contable/revisar/gasto/{idGasto}")
    // Solo si el rol es CONTABLE
    public ResponseEntity<PlanillaGasto> marcarGastoRevisado(@PathVariable Integer idGasto, @RequestParam boolean revisado) {
        PlanillaGasto gastoItem = aprobacionService.marcarGastoRevisado(idGasto, revisado);
        return ResponseEntity.ok(gastoItem);
    }
    
    @PutMapping("/contable/guardar-revision/{idPlanilla}")
    // Cambia el estado de la planilla a REVISADO si todos los gastos han sido marcados como 'sí'
    public ResponseEntity<PlanillaGasto> guardarRevisionContable(@PathVariable Integer idPlanilla) {
        PlanillaGasto planilla = aprobacionService.guardarRevisionPlanilla(idPlanilla);
        return ResponseEntity.ok(planilla); // Estado cambia a REVISADO (para Financiero)
    }

    // --- Acciones de Financiero ---

    @PutMapping("/financiero/aprobar/{idPlanilla}")
    // Solo si el rol es FINANCIERO y la planilla está en REVISADO
    public ResponseEntity<PlanillaGasto> aprobarFinanciero(@PathVariable Integer idPlanilla) {
        PlanillaGasto planilla = aprobacionService.aprobarPorFinanciero(idPlanilla);
        return ResponseEntity.ok(planilla); // Estado cambia a APROBADO
    }

    @PutMapping("/financiero/liquidar/{idFondo}")
    public ResponseEntity<Fondo> liquidarFondo(@PathVariable Integer idFondo, @RequestBody(required = false) TransaccionDTO transaccion) {
        // Maneja los 3 casos (Saldo 0, Saldo > 0, Saldo < 0) y realiza la liquidación.
        Fondo fondoLiquidado = fondoService.liquidarFondo(idFondo, transaccion);
        return ResponseEntity.ok(fondoLiquidado);
    }
    
    @PutMapping("/financiero/cerrar-caja/{idFondo}")
    public ResponseEntity<Fondo> cerrarCajaChica(@PathVariable Integer idFondo) {
        // Flujo final de Caja Chica
        Fondo fondoCerrado = fondoService.cerrarCajaChica(idFondo);
        return ResponseEntity.ok(fondoCerrado);
    }
    
    // --- Acción Común de Rechazo ---
    
    @PutMapping("/rechazar/{idPlanilla}")
    public ResponseEntity<PlanillaGasto> rechazarPlanilla(@PathVariable Integer idPlanilla, @RequestParam String comentario) {
        // Permite a Aprobador de Perfil y Financiero rechazar.
        PlanillaGasto planillaRechazada = aprobacionService.rechazarPlanilla(idPlanilla, comentario);
        return ResponseEntity.ok(planillaRechazada);
    }
}