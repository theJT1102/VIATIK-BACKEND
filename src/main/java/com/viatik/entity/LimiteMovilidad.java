package com.viatik.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "LIMITES_MOVILIDAD")
@Data
public class LimiteMovilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLimite;

    // FK a CATEGORIAS_GASTO (solo categorías tipo MOVILIDAD)
    @Column(name = "id_categoria", unique = true, nullable = false)
    private Integer idCategoria;

    @Column(name = "limite_soles", nullable = false)
    private BigDecimal limiteSoles;

    @Column(name = "limite_dolares", nullable = false)
    private BigDecimal limiteDolares;

	public Integer getIdLimite() {
		return idLimite;
	}

	public void setIdLimite(Integer idLimite) {
		this.idLimite = idLimite;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public BigDecimal getLimiteSoles() {
		return limiteSoles;
	}

	public void setLimiteSoles(BigDecimal limiteSoles) {
		this.limiteSoles = limiteSoles;
	}

	public BigDecimal getLimiteDolares() {
		return limiteDolares;
	}

	public void setLimiteDolares(BigDecimal limiteDolares) {
		this.limiteDolares = limiteDolares;
	}
    
    
}