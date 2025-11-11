package com.viatik.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.viatik.config.JsonToMapConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FONDOS")
@Data
public class Fondo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFondo;

    @Column(name = "id_rendidor")
    private Integer idRendidor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_fondo")
    private TipoFondo tipoFondo; // CAJA_CHICA, ENTREGA_A_RENDIR, REEMBOLSO

    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "monto_solicitado")
    private BigDecimal montoSolicitado;

    @Column(name = "saldo_actual")
    private BigDecimal saldoActual;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_fondo")
    private EstadoFondo estadoFondo; // ABIERTO, DESEMBOLSADO, LIQUIDADO, CANCELADO

    @Column(name = "comentario_rechazo")
    private String comentarioRechazo;

    @Column(name = "id_area_cc")
    private Integer idAreaCc;

    @Column(name = "es_cerrada")
    private Boolean esCerrada;
    
    // Transacciones consolidadas (JSON)
    @Convert(converter = JsonToMapConverter.class)
    @Column(name = "transacciones", columnDefinition = "JSON")
    private JsonNode transacciones; // Historial de Desembolsos/Devoluciones

    public enum TipoFondo {
        CAJA_CHICA, ENTREGA_A_RENDIR, REEMBOLSO
    }
    
    public enum EstadoFondo {
        ABIERTO, DESEMBOLSADO, LIQUIDADO, CANCELADO
    }

	public Integer getIdFondo() {
		return idFondo;
	}

	public void setIdFondo(Integer idFondo) {
		this.idFondo = idFondo;
	}

	public Integer getIdRendidor() {
		return idRendidor;
	}

	public void setIdRendidor(Integer idRendidor) {
		this.idRendidor = idRendidor;
	}

	public TipoFondo getTipoFondo() {
		return tipoFondo;
	}

	public void setTipoFondo(TipoFondo tipoFondo) {
		this.tipoFondo = tipoFondo;
	}

	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public BigDecimal getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}

	public EstadoFondo getEstadoFondo() {
		return estadoFondo;
	}

	public void setEstadoFondo(EstadoFondo estadoFondo) {
		this.estadoFondo = estadoFondo;
	}

	public String getComentarioRechazo() {
		return comentarioRechazo;
	}

	public void setComentarioRechazo(String comentarioRechazo) {
		this.comentarioRechazo = comentarioRechazo;
	}

	public Integer getIdAreaCc() {
		return idAreaCc;
	}

	public void setIdAreaCc(Integer idAreaCc) {
		this.idAreaCc = idAreaCc;
	}

	public Boolean getEsCerrada() {
		return esCerrada;
	}

	public void setEsCerrada(Boolean esCerrada) {
		this.esCerrada = esCerrada;
	}

	public JsonNode getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(JsonNode transacciones) {
		this.transacciones = transacciones;
	}
    
    
}