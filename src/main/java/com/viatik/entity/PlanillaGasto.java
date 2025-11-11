package com.viatik.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.viatik.config.JsonToMapConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PLANILLAS_Y_GASTOS")
@Data
public class PlanillaGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_registro", nullable = false)
    private TipoRegistro tipoRegistro; // PLANILLA o GASTO

    // Campos de Planilla (pueden ser NULL si tipo_registro = GASTO)
    @Column(name = "id_fondo", nullable = false)
    private Integer idFondo;
    
    @Column(name = "id_planilla_padre")
    private Integer idPlanillaPadre; // FK a id_registro si es GASTO

    @Column(name = "nombre_planilla")
    private String nombrePlanilla;
    
    @Column(name = "descripcion_planilla")
    private String descripcionPlanilla;

    @Enumerated(EnumType.STRING)
    private Moneda moneda;
    
    @Column(name = "id_perfil")
    private Integer idPerfil;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_planilla")
    private EstadoPlanilla estadoPlanilla; // ABIERTO, EN_PROCESO, REVISADO, APROBADO, RECHAZADO

    // Campos de Gasto (pueden ser NULL si tipo_registro = PLANILLA)
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_gasto")
    private TipoGasto tipoGasto; // SUSTENTADO, MOVILIDAD, ARTICULO

    @Column(name = "ruc_proveedor")
    private String rucProveedor;
    
    @Column(name = "serie_documento")
    private String serieDocumento;
    
    @Column(name = "fecha_contabilizacion")
    private LocalDate fechaContabilizacion;
    
    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;
    
    private String comentario;
    
    @Column(name = "estado_revisado")
    private Boolean estadoRevisado;

    // Detalle del Gasto (JSON) - Útil solo cuando tipo_registro = GASTO
    @Convert(converter = JsonToMapConverter.class)
    @Column(name = "detalle_gasto", columnDefinition = "JSON")
    private JsonNode detalleGasto;

    // Campo Común
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    public enum TipoRegistro {
        PLANILLA, GASTO
    }
    
    public enum Moneda {
        SOL, USD, EUR
    }

    public enum EstadoPlanilla {
        ABIERTO, EN_PROCESO, REVISADO, APROBADO, RECHAZADO
    }

    public enum TipoGasto {
        SUSTENTADO, MOVILIDAD, ARTICULO
    }

	public Integer getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Integer getIdFondo() {
		return idFondo;
	}

	public void setIdFondo(Integer idFondo) {
		this.idFondo = idFondo;
	}

	public Integer getIdPlanillaPadre() {
		return idPlanillaPadre;
	}

	public void setIdPlanillaPadre(Integer idPlanillaPadre) {
		this.idPlanillaPadre = idPlanillaPadre;
	}

	public String getNombrePlanilla() {
		return nombrePlanilla;
	}

	public void setNombrePlanilla(String nombrePlanilla) {
		this.nombrePlanilla = nombrePlanilla;
	}

	public String getDescripcionPlanilla() {
		return descripcionPlanilla;
	}

	public void setDescripcionPlanilla(String descripcionPlanilla) {
		this.descripcionPlanilla = descripcionPlanilla;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public EstadoPlanilla getEstadoPlanilla() {
		return estadoPlanilla;
	}

	public void setEstadoPlanilla(EstadoPlanilla estadoPlanilla) {
		this.estadoPlanilla = estadoPlanilla;
	}

	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public String getRucProveedor() {
		return rucProveedor;
	}

	public void setRucProveedor(String rucProveedor) {
		this.rucProveedor = rucProveedor;
	}

	public String getSerieDocumento() {
		return serieDocumento;
	}

	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}

	public LocalDate getFechaContabilizacion() {
		return fechaContabilizacion;
	}

	public void setFechaContabilizacion(LocalDate fechaContabilizacion) {
		this.fechaContabilizacion = fechaContabilizacion;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean getEstadoRevisado() {
		return estadoRevisado;
	}

	public void setEstadoRevisado(Boolean estadoRevisado) {
		this.estadoRevisado = estadoRevisado;
	}

	public JsonNode getDetalleGasto() {
		return detalleGasto;
	}

	public void setDetalleGasto(JsonNode detalleGasto) {
		this.detalleGasto = detalleGasto;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
    
    
}