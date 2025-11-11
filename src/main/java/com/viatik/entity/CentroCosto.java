package com.viatik.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CENTRO_COSTO")
@Data
public class CentroCosto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCentroCosto;

    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;
}