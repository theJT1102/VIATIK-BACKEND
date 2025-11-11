package com.viatik.dto;

public class AuthTokenDTO {
    private String token;
    private String rolPrincipal;
    private Integer idUsuario;
    private String nombreCompleto;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRolPrincipal() {
		return rolPrincipal;
	}
	public void setRolPrincipal(String rolPrincipal) {
		this.rolPrincipal = rolPrincipal;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}