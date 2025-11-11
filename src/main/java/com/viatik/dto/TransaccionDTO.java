package com.viatik.dto;

import java.math.BigDecimal;

public class TransaccionDTO {
    
    private String tipoMovimiento; // DESEMBOLSO, DEVOLUCION, REEMBOLSO_A_RENDIDOR, REEMBOLSO_CAJA_CHICA
    private BigDecimal monto;
    private String cuentaBancaria;
    private String numeroOperacion;
    private String estado; // PENDIENTE (solo para DEVOLUCION), APROBADO
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

    
}