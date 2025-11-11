package com.viatik.dto;

import java.util.List;

import com.viatik.entity.PlanillaGasto.Moneda;

public class PlanillaCreacionDTO {
    
    private Integer idFondo;
    private String nombre;
    private String descripcion;
    private Moneda moneda;
    private Integer idPerfil;
    
    // Lista de gastos a adjuntar a esta planilla (solo en la creación inicial)
    private List<GastoCreacionBaseDTO> gastos; // Ver clase abajo

	public Integer getIdFondo() {
		return idFondo;
	}

	public void setIdFondo(Integer idFondo) {
		this.idFondo = idFondo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public List<GastoCreacionBaseDTO> getGastos() {
		return gastos;
	}

	public void setGastos(List<GastoCreacionBaseDTO> gastos) {
		this.gastos = gastos;
	}
	
	
}