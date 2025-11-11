package com.viatik.dto;

import java.math.BigDecimal;

import com.viatik.entity.Fondo.TipoFondo;

public class FondoCreacionDTO {
    
    private TipoFondo tipoFondo; // CAJA_CHICA, ENTREGA_A_RENDIR, REEMBOLSO (aunque este último es especial)
    private Integer idRendidor;
    private BigDecimal montoSolicitado; // Solo aplica a Caja Chica y Entrega a Rendir
    private Integer idAreaCc; // Solo necesario si es CAJA_CHICA
	public TipoFondo getTipoFondo() {
		return tipoFondo;
	}
	public void setTipoFondo(TipoFondo tipoFondo) {
		this.tipoFondo = tipoFondo;
	}
	public Integer getIdRendidor() {
		return idRendidor;
	}
	public void setIdRendidor(Integer idRendidor) {
		this.idRendidor = idRendidor;
	}
	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	public Integer getIdAreaCc() {
		return idAreaCc;
	}
	public void setIdAreaCc(Integer idAreaCc) {
		this.idAreaCc = idAreaCc;
	}

    // Getters y Setters (Omitidos por brevedad)
    // ...
}