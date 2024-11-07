package com.iudigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.iudigital.domain.Funcionario;
import com.iudigital.utilidades.ConnectionUtil;

public class FuncionarioDao {
	
private static final String OBTENER_FUNCIONARIOS = "select *, ec.nombre AS estado_civil,"
		+ "td.nombre AS tipo_documento from funcionarios f JOIN "
		+ "estado_civil ec ON f.estado_civil_id = ec.estado_civil_id JOIN "
		+ "tipo_documento td ON f.tipo_documento_id = td.tipo_documento_id;";
	
	private static final String CREAR_FUNCIONATRIO = "insert into funcionarios (numero_identificacion, nombres, apellidos, sexo, direccion, telefono, fecha_nacimiento, estado_civil_id, tipo_documento_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String OBTENER_FUNCIONARIO_POR_ID = "select * from funcionarios where funcionario_id  = ?";
	
	private static final String ACTUALIZAR_FUNCIONARIO = "update funcionarios set "
			+ "numero_identificacion = ?, "
			+ "nombres = ?, "
			+ "apellidos = ?, "
			+ "sexo = ?, "
			+ "direccion = ?, "
			+ "telefono = ?, "
			+ "fecha_nacimiento = ?, "
			+ "estado_civil_id = ?, "
			+ "tipo_documento_id = ? "
			+ "where funcionario_id = ?";
	
	private static final String ELIMINAR_FUNCIONARIO= "delete from funcionarios where funcionario_id = ?";
	
	
	public List<Funcionario> obtenerFuncionarios() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Funcionario> funcionarios = new ArrayList<>();
		
		try {
			
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(OBTENER_FUNCIONARIOS);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setFuncionarioId(resultSet.getInt("funcionario_id"));
				funcionario.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
				funcionario.setNombres(resultSet.getString("nombres"));
				funcionario.setApellidos(resultSet.getString("apellidos"));
				funcionario.setSexo(resultSet.getString("sexo"));
				funcionario.setDireccion(resultSet.getString("direccion"));
				funcionario.setTelefono(resultSet.getString("telefono"));
				funcionario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
				funcionario.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
				funcionario.setFechaActualizacion(resultSet.getObject("fecha_actualizacion", LocalDateTime.class));
				funcionario.setEstadoCivil(resultSet.getString("estado_civil"));
				funcionario.setTipoDocumento(resultSet.getString("tipo_documento"));
				funcionarios.add(funcionario);
			}			
			return funcionarios;
			
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

		public void crearFuncionario(Funcionario funcionario) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ConnectionUtil.getConnection();
				preparedStatement = connection.prepareStatement(CREAR_FUNCIONATRIO);
				
				preparedStatement.setString(1, funcionario.getNumeroIdentificacion());
				preparedStatement.setString(2, funcionario.getNombres());
				preparedStatement.setString(3, funcionario.getApellidos());
				preparedStatement.setString(4, funcionario.getSexo());
				preparedStatement.setString(5, funcionario.getDireccion());
				preparedStatement.setString(6, funcionario.getTelefono());
				preparedStatement.setDate(7, funcionario.getFechaNacimiento());
				preparedStatement.setInt(8, funcionario.getEstadoCivilId());
				preparedStatement.setInt(9, funcionario.getTipoDocumentoId());
				
			} finally {
				
				if (connection != null) {
	                connection.close();
	            }
	      
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
			}
			
		}
		
		public Funcionario obtenerFuncionarioPorId(int funcionarioId) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			Funcionario funcionario = null;
			
			try {
				connection = ConnectionUtil.getConnection();
				preparedStatement = connection.prepareStatement(OBTENER_FUNCIONARIO_POR_ID);
				preparedStatement.setInt(1, funcionarioId);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					funcionario = new Funcionario();
					funcionario.setFuncionarioId(resultSet.getInt("funcionario_id"));
					funcionario.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
					funcionario.setNombres(resultSet.getString("nombres"));
					funcionario.setApellidos(resultSet.getString("apellidos"));
					funcionario.setSexo(resultSet.getString("sexo"));
					funcionario.setDireccion(resultSet.getString("direccion"));
					funcionario.setTelefono(resultSet.getString("telefono"));
					funcionario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
					funcionario.setEstadoCivilId(resultSet.getInt("estado_civil_id"));
					funcionario.setTipoDocumentoId(resultSet.getInt("tipo_documento_id"));		
				}
				return funcionario;
				
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
		
		public void actualizarFuncionario(Funcionario funcionario, int idFuncionario) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ConnectionUtil.getConnection();
				preparedStatement = connection.prepareStatement(ACTUALIZAR_FUNCIONARIO);
				preparedStatement.setString(1, funcionario.getNumeroIdentificacion());
				preparedStatement.setString(2, funcionario.getNombres());
				preparedStatement.setString(3, funcionario.getApellidos());
				preparedStatement.setString(4, funcionario.getSexo());
				preparedStatement.setString(5, funcionario.getDireccion());
				preparedStatement.setString(6, funcionario.getTelefono());
				preparedStatement.setDate(7, funcionario.getFechaNacimiento());
				preparedStatement.setInt(8, funcionario.getEstadoCivilId());
				preparedStatement.setInt(9, funcionario.getTipoDocumentoId());
				preparedStatement.executeQuery();
			} finally {
				
					if (connection != null) {
		                connection.close();
		            }
		      
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
			}
			
		}
	
		public void eliminarFuncionario(int idFuncionario) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = ConnectionUtil.getConnection();
				preparedStatement = connection.prepareStatement(ELIMINAR_FUNCIONARIO);
				
				preparedStatement.setInt(1, idFuncionario);
				preparedStatement.executeQuery();
				
			} finally {
				
			
			}
		}
}
