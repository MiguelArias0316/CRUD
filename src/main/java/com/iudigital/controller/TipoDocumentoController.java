package com.iudigital.controller;

import java.sql.SQLException;
import java.util.List;

import com.iudigital.dao.TipoDocumentoDao;
import com.iudigital.domain.TipoDocumento;

public class TipoDocumentoController {
private TipoDocumentoDao tipoDocumentoDao;
	
	public TipoDocumentoController() {
		tipoDocumentoDao = new TipoDocumentoDao();
	}
	
	public List<TipoDocumento> obtenerTipoDocumento() throws SQLException{
		return tipoDocumentoDao.obtenerTipoDocumento();
	}
}
