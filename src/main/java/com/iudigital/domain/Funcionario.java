package com.iudigital.domain;
import java.sql.Date;
import java.time.LocalDateTime;

public class Funcionario {
	private int funcionarioId;
	private String numeroIdentificacion;
	private String nombres;
	private String apellidos;
	private String sexo;
	private String direccion;
	private String telefono;
	private Date fechaNacimiento;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaActualizacion;
	private int estadoCivilId;
	private int tipoDocumentoId;
	private String estadoCivil;
	private String tipoDocumento;
	
	public int getFuncionarioId() {
		return funcionarioId;
	}
	public void setFuncionarioId(int funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public int getEstadoCivilId() {
		return estadoCivilId;
	}
	public void setEstadoCivilId(int estadoCivilId) {
		this.estadoCivilId = estadoCivilId;
	}
	public int getTipoDocumentoId() {
		return tipoDocumentoId;
	}
	public void setTipoDocumentoId(int tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}
	
	@Override
	public String toString() {
		return nombres + " " +apellidos +" - "+ numeroIdentificacion;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	
}
