package dao;

import java.util.List;

import model.Dependente;

public interface DependenteDAO extends GenericDAO<Dependente> {

	public List<Dependente> findByNome(String nome);

	public List<Dependente> findById(int id);

}
