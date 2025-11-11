package com.viatik.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CATEGORIAS_GASTO")
@Data
public class CategoriaGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_categoria", nullable = false)
    private TipoCategoria tipoCategoria; // GASTO o MOVILIDAD
    
    public enum TipoCategoria {
        GASTO, MOVILIDAD
    }
}