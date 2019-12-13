package br.ufc.persistencia.dao;

import br.ufc.persistencia.model.Bean;

public interface GenericDAO<T extends Bean> {

	public void insert(T object); /* --- Funcionando --- */

	public void delete(T t);

	public void delete(Object id);
	
	public void update(T t);

	public void close();
}