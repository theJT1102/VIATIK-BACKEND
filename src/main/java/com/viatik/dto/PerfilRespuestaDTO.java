package com.viatik.dto;

import java.util.List;

import com.viatik.dto.PerfilCreacionDTO.UsuarioAsignacionDTO;

public class PerfilRespuestaDTO {
    
    private Integer idPerfil;
    private String nombre;
    private String descripcion;
    private String nombreEmpresa; // Nombre en lugar de ID
    
    // Asignaciones ya procesadas y listas para el frontend
    private List<UsuarioAsignacionDTO> usuariosAsignados; 
    private List<Integer> almacenesAsignados; 
    private List<Integer> categoriasAsignadas; 
    private List<Integer> proyectosAsignados;
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
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public List<UsuarioAsignacionDTO> getUsuariosAsignados() {
		return usuariosAsignados;
	}
	public void setUsuariosAsignados(List<UsuarioAsignacionDTO> usuariosAsignados) {
		this.usuariosAsignados = usuariosAsignados;
	}
	public List<Integer> getAlmacenesAsignados() {
		return almacenesAsignados;
	}
	public void setAlmacenesAsignados(List<Integer> almacenesAsignados) {
		this.almacenesAsignados = almacenesAsignados;
	}
	public List<Integer> getCategoriasAsignadas() {
		return categoriasAsignadas;
	}
	public void setCategoriasAsignadas(List<Integer> categoriasAsignadas) {
		this.categoriasAsignadas = categoriasAsignadas;
	}
	public List<Integer> getProyectosAsignados() {
		return proyectosAsignados;
	}
	public void setProyectosAsignados(List<Integer> proyectosAsignados) {
		this.proyectosAsignados = proyectosAsignados;
	}

    // Getters y Setters (Omitidos por brevedad)
    // ...
}