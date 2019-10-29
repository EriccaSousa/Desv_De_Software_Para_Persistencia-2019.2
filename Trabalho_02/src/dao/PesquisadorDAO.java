package dao;

import java.util.List;

import model.Funcionario;
import model.Pesquisador;

public interface PesquisadorDAO extends GenericDAO<Pesquisador> {

	public List<Pesquisador> findByNome(String nome);

	public List<Pesquisador> findById(int id);

	public void save(Funcionario funcionario);

}
