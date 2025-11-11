package com.viatik.dto;

import java.util.List;

public class PerfilCreacionDTO {
    
    private String nombre;
    private String descripcion;
    private Integer idEmpresa;

    // Asignaciones de Usuarios (Consolidado)
    private List<UsuarioAsignacionDTO> usuarios; // Ver clase anidada abajo
    
    // Asignaciones de Recursos (Consolidado)
    private List<Integer> almacenes;
    private List<Integer> categorias;
    private List<Integer> proyectos;

    

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



	public List<UsuarioAsignacionDTO> getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(List<UsuarioAsignacionDTO> usuarios) {
		this.usuarios = usuarios;
	}



	public List<Integer> getAlmacenes() {
		return almacenes;
	}



	public void setAlmacenes(List<Integer> almacenes) {
		this.almacenes = almacenes;
	}



	public List<Integer> getCategorias() {
		return categorias;
	}



	public void setCategorias(List<Integer> categorias) {
		this.categorias = categorias;
	}



	public List<Integer> getProyectos() {
		return proyectos;
	}



	public void setProyectos(List<Integer> proyectos) {
		this.proyectos = proyectos;
	}



	// Clase interna/anidada para la estructura de usuarios
    public static class UsuarioAsignacionDTO {
        public Integer getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(Integer idUsuario) {
			this.idUsuario = idUsuario;
		}
		public String getRol() {
			return rol;
		}
		public void setRol(String rol) {
			this.rol = rol;
		}
		private Integer idUsuario;
        private String rol; // 'FINANCIERO', 'CONTABLE', 'APROBADOR', 'RENDIDOR'

        // Getters y Setters (Omitidos por brevedad)
        // ...
    }
}