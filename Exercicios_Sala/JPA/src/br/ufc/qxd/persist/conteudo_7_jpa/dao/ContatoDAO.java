package br.ufc.qxd.persist.conteudo_7_jpa.dao;

import java.util.List;

import br.ufc.qxd.persist.conteudo_7_jpa.model.Contato;

public interface ContatoDAO extends GenericDAO<Contato> {

	
	
	
	public List<Contato> findByNome(String nome);
	public List<Contato> findByParteDoNome(String nome);
}
