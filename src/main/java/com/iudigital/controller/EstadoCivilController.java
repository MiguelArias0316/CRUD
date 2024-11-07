package com.iudigital.controller;

import java.sql.SQLException;
import java.util.List;

import com.iudigital.dao.EstadoCivilDao;
import com.iudigital.domain.EstadoCivil;

public class EstadoCivilController {
	
private EstadoCivilDao estadoCivilDao;
	
	public EstadoCivilController() {
		estadoCivilDao = new EstadoCivilDao();
	}
	
	public List<EstadoCivil> obtenerEstadoCivil() throws SQLException{
		return estadoCivilDao.obtenerEstadoCivil();
	}
}
