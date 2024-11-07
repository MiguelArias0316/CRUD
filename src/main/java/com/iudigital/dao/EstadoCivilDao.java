package com.iudigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iudigital.domain.EstadoCivil;

import com.iudigital.utilidades.ConnectionUtil;

public class EstadoCivilDao {
	
	private static final String OBTENER_ESTADO_CIVIL = "select * from estado_civil";
	
	public List<EstadoCivil> obtenerEstadoCivil() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<EstadoCivil> estadosCiviles = new ArrayList<>();
		
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(OBTENER_ESTADO_CIVIL);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil.setEstadoCivilId(resultSet.getInt("estado_civil_id"));
				estadoCivil.setNombre(resultSet.getString("nombre"));
				estadosCiviles.add(estadoCivil);
			}			
			return estadosCiviles;
			
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
