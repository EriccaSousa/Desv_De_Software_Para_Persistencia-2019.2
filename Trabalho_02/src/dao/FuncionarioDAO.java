package dao;

import java.util.List;

import model.Funcionario;

public interface FuncionarioDAO extends GenericDAO<Funcionario> {

	public List<Funcionario> findByNome(String nome);

	public List<Funcionario> findById(int id);

}
