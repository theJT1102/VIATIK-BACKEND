package com.viatik.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.viatik.config.JsonToMapConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PERFILES")
@Data
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfil;

    private String nombre;
    private String descripcion;
    
    // FK a EMPRESAS
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    // Campos JSON consolidados
    @Convert(converter = JsonToMapConverter.class)
    @Column(name = "usuarios_asignados", columnDefinition = "JSON")
    private JsonNode usuariosAsignados; // [{'id': 1, 'rol': 'FINANCIERO'}, ...]

    @Convert(converter = JsonToMapConverter.class)
    @Column(name = "almacenes_asignados", columnDefinition = "JSON")
    private JsonNode almacenesAsignados; // [1, 2, 3]

    @Convert(converter = JsonToMapConverter.class)
    @Column(name = "categorias_asignadas", columnDefinition = "JSON")
    private JsonNode categoriasAsignadas; // [10, 11, 12]

    @Convert(converter = JsonToMapConverter.class)
    @Column(name = "proyectos_asignados", columnDefinition = "JSON")
    private JsonNode proyectosAsignados; // [50, 51]

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
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

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public JsonNode getUsuariosAsignados() {
		return usuariosAsignados;
	}

	public void setUsuariosAsignados(JsonNode usuariosAsignados) {
		this.usuariosAsignados = usuariosAsignados;
	}

	public JsonNode getAlmacenesAsignados() {
		return almacenesAsignados;
	}

	public void setAlmacenesAsignados(JsonNode almacenesAsignados) {
		this.almacenesAsignados = almacenesAsignados;
	}

	public JsonNode getCategoriasAsignadas() {
		return categoriasAsignadas;
	}

	public void setCategoriasAsignadas(JsonNode categoriasAsignadas) {
		this.categoriasAsignadas = categoriasAsignadas;
	}

	public JsonNode getProyectosAsignados() {
		return proyectosAsignados;
	}

	public void setProyectosAsignados(JsonNode proyectosAsignados) {
		this.proyectosAsignados = proyectosAsignados;
	}
    
    
}