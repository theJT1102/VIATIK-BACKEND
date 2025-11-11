package com.viatik.controller;

import com.viatik.dto.FondoCreacionDTO;
import com.viatik.dto.GastoCreacionBaseDTO;
import com.viatik.dto.PlanillaCreacionDTO;
import com.viatik.entity.Fondo;
import com.viatik.entity.PlanillaGasto;
import com.viatik.service.FondoService;
import com.viatik.service.PlanillaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendidor")
// Nota: Este controller debería estar protegido para el rol RENDIDOR
public class RendicionController {

    private final FondoService fondoService;
    private final PlanillaService planillaService;

    public RendicionController(FondoService fondoService, PlanillaService planillaService) {
        this.fondoService = fondoService;
        this.planillaService = planillaService;
    }

    // --- Flujo de Solicitud de Fondos (Caja Chica / Entrega a Rendir / Reembolso) ---

    @PostMapping("/fondos/solicitar")
    public ResponseEntity<Fondo> solicitarFondo(@RequestBody FondoCreacionDTO fondoDto) {
        // La lógica de aprobación inicial (Financiero) se inicia en el Service
        Fondo nuevoFondo = fondoService.solicitarFondo(fondoDto);
        return new ResponseEntity<>(nuevoFondo, HttpStatus.CREATED);
    }
    
    // --- Flujo de Registro de Planilla ---

    @PostMapping("/planillas")
    public ResponseEntity<PlanillaGasto> crearPlanilla(@RequestBody PlanillaCreacionDTO planillaDto) {
        // Crea la Planilla (tipo_registro=PLANILLA) y sus gastos iniciales
        PlanillaGasto planilla = planillaService.crearPlanillaConGastos(planillaDto);
        return new ResponseEntity<>(planilla, HttpStatus.CREATED);
    }
    
    @PostMapping("/planillas/{idPlanilla}/gasto")
    public ResponseEntity<PlanillaGasto> agregarGasto(@PathVariable Integer idPlanilla, @RequestBody GastoCreacionBaseDTO gastoDto) {
        // Agrega un ítem de gasto (tipo_registro=GASTO) a una planilla existente.
        PlanillaGasto nuevoGastoItem = planillaService.agregarGastoAPlanilla(idPlanilla, gastoDto);
        return new ResponseEntity<>(nuevoGastoItem, HttpStatus.CREATED);
    }
    
    @PutMapping("/planillas/{idPlanilla}/enviar")
    public ResponseEntity<PlanillaGasto> enviarParaAprobacion(@PathVariable Integer idPlanilla) {
        // Cambia el estado a EN_PROCESO para que lo vea el Aprobador de Perfil.
        PlanillaGasto planilla = planillaService.enviarParaAprobacion(idPlanilla);
        return ResponseEntity.ok(planilla);
    }
    
    // Opcional: Para que el rendidor pueda ver sus fondos/planillas
    @GetMapping("/planillas/mis-planillas")
    public ResponseEntity<List<PlanillaGasto>> getMisPlanillas() {
        // Se asume que el ID de usuario se extrae del token de seguridad
        Integer idRendidor = 1; // EJEMPLO: Reemplazar con SecurityContextHolder.getContext().getAuthentication().getPrincipal().getId()
        List<PlanillaGasto> planillas = planillaService.findPlanillasByRendidor(idRendidor);
        return ResponseEntity.ok(planillas);
    }
}