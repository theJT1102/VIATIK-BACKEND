package com.viatik.dto;

import java.math.BigDecimal;
import java.util.List;

public class DetalleSustentadoDTO {
    
    // Se requiere una lista para las líneas del gasto sustentado (máximo 3)
    private List<LineaGastoSustentadoDTO> lineas; 
    
    // Campos calculados por el backend, pero mostrados al usuario
    private BigDecimal precioBruto; 
    private BigDecimal igv; 
    private BigDecimal total; 

    public static class LineaGastoSustentadoDTO {
        private Integer idCategoria;
        private String tipoImpuesto; // I18, I10, EX
        private BigDecimal totalLinea; // Monto de la factura/línea
        private Integer idCentroCosto;
		public Integer getIdCategoria() {
			return idCategoria;
		}
		public void setIdCategoria(Integer idCategoria) {
			this.idCategoria = idCategoria;
		}
		public String getTipoImpuesto() {
			return tipoImpuesto;
		}
		public void setTipoImpuesto(String tipoImpuesto) {
			this.tipoImpuesto = tipoImpuesto;
		}
		public BigDecimal getTotalLinea() {
			return totalLinea;
		}
		public void setTotalLinea(BigDecimal totalLinea) {
			this.totalLinea = totalLinea;
		}
		public Integer getIdCentroCosto() {
			return idCentroCosto;
		}
		public void setIdCentroCosto(Integer idCentroCosto) {
			this.idCentroCosto = idCentroCosto;
		}

        
    }
    

	public List<LineaGastoSustentadoDTO> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaGastoSustentadoDTO> lineas) {
		this.lineas = lineas;
	}

	public BigDecimal getPrecioBruto() {
		return precioBruto;
	}

	public void setPrecioBruto(BigDecimal precioBruto) {
		this.precioBruto = precioBruto;
	}

	public BigDecimal getIgv() {
		return igv;
	}

	public void setIgv(BigDecimal igv) {
		this.igv = igv;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}