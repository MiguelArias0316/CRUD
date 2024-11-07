package com.iudigital.domain;

public class EstadoCivil {
	
	private int estadoCivilId;
	private String nombre;
	
	public int getEstadoCivilId() {
		return estadoCivilId;
	}
	public void setEstadoCivilId(int estadoCivilId) {
		this.estadoCivilId = estadoCivilId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
}
