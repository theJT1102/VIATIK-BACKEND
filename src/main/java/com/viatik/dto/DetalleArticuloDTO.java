package com.viatik.dto;

import java.math.BigDecimal;
import java.util.List;

public class DetalleArticuloDTO {
    
    private List<ItemArticuloDTO> items; // Máximo 30 líneas
    
    private BigDecimal precioBruto; 
    private BigDecimal igv; // Siempre I18
    private BigDecimal total; 

    public static class ItemArticuloDTO {
        private Integer idArticulo;
        private Integer idAlmacen;
        private Integer idUnidadMedida;
        private BigDecimal cantidad;
        private BigDecimal precioUnitario; // Máximo 6 decimales
		public Integer getIdArticulo() {
			return idArticulo;
		}
		public void setIdArticulo(Integer idArticulo) {
			this.idArticulo = idArticulo;
		}
		public Integer getIdAlmacen() {
			return idAlmacen;
		}
		public void setIdAlmacen(Integer idAlmacen) {
			this.idAlmacen = idAlmacen;
		}
		public Integer getIdUnidadMedida() {
			return idUnidadMedida;
		}
		public void setIdUnidadMedida(Integer idUnidadMedida) {
			this.idUnidadMedida = idUnidadMedida;
		}
		public BigDecimal getCantidad() {
			return cantidad;
		}
		public void setCantidad(BigDecimal cantidad) {
			this.cantidad = cantidad;
		}
		public BigDecimal getPrecioUnitario() {
			return precioUnitario;
		}
		public void setPrecioUnitario(BigDecimal precioUnitario) {
			this.precioUnitario = precioUnitario;
		}

        
    }
    
	public List<ItemArticuloDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemArticuloDTO> items) {
		this.items = items;
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