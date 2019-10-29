package dao;

import java.util.List;

import model.Departamento;

public interface DepartamentoDAO extends GenericDAO<Departamento> {

	public List<Departamento> findByNome(String nome);

	public List<Departamento> findById(int id);

}
