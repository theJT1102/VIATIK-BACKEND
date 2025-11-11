package com.viatik.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode; // Usamos JsonNode para el detalle variable
import com.viatik.entity.PlanillaGasto.TipoGasto;

public class GastoCreacionBaseDTO {
    
    private TipoGasto tipoGasto; // SUSTENTADO, MOVILIDAD, ARTICULO
    private String rucProveedor;
    private String serieDocumento;
    private LocalDate fechaContabilizacion;
    private LocalDate fechaEmision;
    private String comentario;
    
    // Detalle del gasto: contendrá uno de los DTOs de Detalle serializado a JSON.
    private JsonNode detalle;

	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public String getRucProveedor() {
		return rucProveedor;
	}

	public void setRucProveedor(String rucProveedor) {
		this.rucProveedor = rucProveedor;
	}

	public String getSerieDocumento() {
		return serieDocumento;
	}

	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}

	public LocalDate getFechaContabilizacion() {
		return fechaContabilizacion;
	}

	public void setFechaContabilizacion(LocalDate fechaContabilizacion) {
		this.fechaContabilizacion = fechaContabilizacion;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public JsonNode getDetalle() {
		return detalle;
	}

	public void setDetalle(JsonNode detalle) {
		this.detalle = detalle;
	}

    
}