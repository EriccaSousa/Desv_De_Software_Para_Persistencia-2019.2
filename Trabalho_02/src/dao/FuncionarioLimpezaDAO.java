package dao;

import java.util.List;

import model.Funcionario;
import model.FuncionarioLimpeza;

public interface FuncionarioLimpezaDAO extends GenericDAO<FuncionarioLimpeza> {
	public List<FuncionarioLimpeza> findByNome(String nome);

	public List<FuncionarioLimpeza> findById(int id);

	public void save(Funcionario funcionario);

}
