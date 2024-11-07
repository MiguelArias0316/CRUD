package com.iudigital.controller;

import java.sql.SQLException;
import java.util.List;

import com.iudigital.dao.FuncionarioDao;
import com.iudigital.domain.Funcionario;

public class FuncionarioController {
	
	private FuncionarioDao funcionarioDao;
	
	public FuncionarioController() {
		funcionarioDao = new FuncionarioDao();
	}
	
	public List<Funcionario> obtenerFuncionarios() throws SQLException{
		return funcionarioDao.obtenerFuncionarios();
	}
	public void crearFuncionario(Funcionario funcionario) throws SQLException {
		 funcionarioDao.crearFuncionario(funcionario);
	}
	public Funcionario obtenerFuncionarioPorId(int idFuncionario) throws SQLException {
		return funcionarioDao.obtenerFuncionarioPorId(idFuncionario);
	}
	public void actualizarFuncionario(Funcionario funcionario, int idFuncionario) throws SQLException {
		funcionarioDao.actualizarFuncionario(funcionario, idFuncionario);
	}
	public void eliminarFuncionario(int idFuncionario) throws SQLException {
		funcionarioDao.eliminarFuncionario(idFuncionario);
	}
	
	
}