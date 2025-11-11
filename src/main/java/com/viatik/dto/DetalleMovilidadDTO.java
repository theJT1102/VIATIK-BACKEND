package com.viatik.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DetalleMovilidadDTO {
    
    private Integer idCategoriaMovilidad;
    private List<RutaMovilidadDTO> rutas; // Máximo 30 líneas
    
    private BigDecimal totalGeneral; // Suma de todos los totales de ruta

    public static class RutaMovilidadDTO {
        private String puntoPartida;
        private String puntoDestino;
        private LocalDate fecha;
        private BigDecimal total; // No puede exceder el límite diario
        private String descripcion; // CLIENTES, PROVEEDORES u OTROS
		public String getPuntoPartida() {
			return puntoPartida;
		}
		public void setPuntoPartida(String puntoPartida) {
			this.puntoPartida = puntoPartida;
		}
		public String getPuntoDestino() {
			return puntoDestino;
		}
		public void setPuntoDestino(String puntoDestino) {
			this.puntoDestino = puntoDestino;
		}
		public LocalDate getFecha() {
			return fecha;
		}
		public void setFecha(LocalDate fecha) {
			this.fecha = fecha;
		}
		public BigDecimal getTotal() {
			return total;
		}
		public void setTotal(BigDecimal total) {
			this.total = total;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

        
    }
    

	public Integer getIdCategoriaMovilidad() {
		return idCategoriaMovilidad;
	}

	public void setIdCategoriaMovilidad(Integer idCategoriaMovilidad) {
		this.idCategoriaMovilidad = idCategoriaMovilidad;
	}

	public List<RutaMovilidadDTO> getRutas() {
		return rutas;
	}

	public void setRutas(List<RutaMovilidadDTO> rutas) {
		this.rutas = rutas;
	}

	public BigDecimal getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(BigDecimal totalGeneral) {
		this.totalGeneral = totalGeneral;
	}
}