package br.ufc.persistencia.dao;

import java.util.List;

import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.Secretario;

public interface FindDAO<T> {

	public T findById(Object id); // Funcionando

	public List<T> findByNome(String nome); // Não funciona

	public List<T> findAll(); // Funcionando
}
