package co.empresa.imc.dao;

import java.sql.SQLException;
import java.util.List;

import co.edu.ufps.crud.model.User;
import co.empresa.imc.model.Paciente;

public interface PacienteDAO {
	public void insertUser(Paciente paciente) throws SQLException;
	public Paciente selectUser(Integer id);
	public List < Paciente > selectAllUsers();
	public boolean deleteUser(int id) throws SQLException;
	public boolean updateUser(Paciente paciente) throws SQLException;
}
