package com.viatik.service;

import com.viatik.dto.FondoCreacionDTO;
import com.viatik.dto.TransaccionDTO;
import com.viatik.entity.Fondo;
import com.viatik.entity.Fondo.EstadoFondo;
import com.viatik.entity.Fondo.TipoFondo;
import com.viatik.repository.FondoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class FondoService {

    private final FondoRepository fondoRepository;
    private final JsonService jsonService;
    // ... PlanillaGastoRepository para obtener el monto total rendido

    public FondoService(FondoRepository fondoRepository, JsonService jsonService) {
        this.fondoRepository = fondoRepository;
        this.jsonService = jsonService;
    }

    public Fondo solicitarFondo(FondoCreacionDTO dto) {
        Fondo fondo = new Fondo();
        fondo.setIdRendidor(dto.getIdRendidor());
        fondo.setTipoFondo(dto.getTipoFondo());
        fondo.setFechaSolicitud(LocalDateTime.now());
        fondo.setMontoSolicitado(dto.getMontoSolicitado() != null ? dto.getMontoSolicitado() : BigDecimal.ZERO);
        fondo.setSaldoActual(BigDecimal.ZERO); // Inicialmente cero, se actualiza en el desembolso
        fondo.setEstadoFondo(EstadoFondo.ABIERTO);
        fondo.setIdAreaCc(dto.getIdAreaCc());
        
        // **NOTA:** El flujo de aprobación inicial (Financiero) se inicia aquí.
        // Si es REEMBOLSO, pasa directo a DESEMBOLSADO/APROBADO.

        return fondoRepository.save(fondo);
    }
    
    public Fondo registrarTransaccion(Integer idFondo, TransaccionDTO transaccionDto) {
        Fondo fondo = fondoRepository.findById(idFondo)
                                     .orElseThrow(() -> new RuntimeException("Fondo no encontrado"));

        // 1. Convertir DTO a JsonNode
        // 2. Agregar al campo 'transacciones' del fondo usando JsonService.addToArray()
        
        // **LÓGICA CRÍTICA:** Actualizar saldo_actual según el tipoMovimiento.
        
        fondo.setTransacciones(jsonService.addToArray(fondo.getTransacciones(), transaccionDto));
        return fondoRepository.save(fondo);
    }

    public Fondo liquidarFondo(Integer idFondo, TransaccionDTO transaccion) {
        Fondo fondo = fondoRepository.findById(idFondo).orElseThrow(() -> new RuntimeException("Fondo no encontrado"));
        
        // 1. Obtener el monto total rendido de las planillas asociadas
        BigDecimal montoRendido = BigDecimal.valueOf(0); // DEBES IMPLEMENTAR ESTA BÚSQUEDA
        
        // 2. Calcular el saldo real (Monto Desembolsado Total - Monto Rendido)
        BigDecimal saldoNeto = fondo.getSaldoActual().subtract(montoRendido);
        
        // 3. Evaluar los 3 casos (0, >0, <0)
        
        if (saldoNeto.compareTo(BigDecimal.ZERO) == 0) {
            // Saldo 0: Procede a liquidar.
            fondo.setEstadoFondo(EstadoFondo.LIQUIDADO);
            
        } else if (saldoNeto.compareTo(BigDecimal.ZERO) > 0) {
            // Saldo > 0 (Rendidor debe devolver): La devolución ya debe estar aprobada.
            if (transaccion == null || !"APROBADO".equals(transaccion.getEstado())) {
                throw new RuntimeException("Falta la devolución aprobada del rendidor.");
            }
            // Registrar la transacción de devolución (ya aprobada) y liquidar.
            registrarTransaccion(idFondo, transaccion);
            fondo.setEstadoFondo(EstadoFondo.LIQUIDADO);

        } else { // Saldo < 0 (Rendidor gastó más): Se requiere un reembolso del Financiero
            if (transaccion == null || !"DESEMBOLSO".equals(transaccion.getTipoMovimiento())) {
                 throw new RuntimeException("Falta registrar el desembolso/reembolso al rendidor.");
            }
            // Registrar la transacción de desembolso (reembolso)
            registrarTransaccion(idFondo, transaccion);
            fondo.setEstadoFondo(EstadoFondo.LIQUIDADO);
        }
        
        return fondoRepository.save(fondo);
    }

    public Fondo cerrarCajaChica(Integer idFondo) {
        Fondo fondo = fondoRepository.findById(idFondo).orElseThrow(() -> new RuntimeException("Fondo no encontrado"));
        
        if (fondo.getTipoFondo() != TipoFondo.CAJA_CHICA) {
            throw new IllegalArgumentException("Solo se puede cerrar una Caja Chica.");
        }
        
        // La lógica de liquidación final debe ocurrir aquí antes del cierre.
        // Asegurar que el saldo esté en cero o liquidado.
        
        fondo.setEsCerrada(true);
        fondo.setEstadoFondo(EstadoFondo.LIQUIDADO);
        return fondoRepository.save(fondo);
    }
}