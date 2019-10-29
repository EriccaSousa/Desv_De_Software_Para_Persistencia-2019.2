package dao;

import java.util.List;

import model.Projeto;

public interface ProjetoDAO extends GenericDAO<Projeto> {

	public List<Projeto> findByNome(String nome);

	public List<Projeto> findById(int id);
}
