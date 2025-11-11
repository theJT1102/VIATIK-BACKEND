package com.viatik.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PROYECTOS")
@Data
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProyecto;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    // FK a EMPRESAS
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
}