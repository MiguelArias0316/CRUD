package com.iudigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iudigital.domain.TipoDocumento;
import com.iudigital.utilidades.ConnectionUtil;

public class TipoDocumentoDao {
private static final String OBTENER_TIPO_DOCUMENTO = "select * from tipo_documento";
	
	public List<TipoDocumento> obtenerTipoDocumento() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<TipoDocumento> TiposDocumentos = new ArrayList<>();
		
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(OBTENER_TIPO_DOCUMENTO);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				TipoDocumento tipoDocumento = new TipoDocumento();
				tipoDocumento.setTipoDocumentoId(resultSet.getInt("tipo_documento_id"));
				tipoDocumento.setNombre(resultSet.getString("nombre"));
				TiposDocumentos.add(tipoDocumento);
			}			
			return TiposDocumentos;
			
		} finally {
			
			 	if (connection != null) {
	                connection.close();
	            }
	      
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            
	            if (resultSet != null) {
	                resultSet.close();
	            }
		}
		
	}
}
