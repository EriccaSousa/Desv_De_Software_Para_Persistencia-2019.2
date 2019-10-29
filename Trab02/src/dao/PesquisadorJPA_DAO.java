package dao;

import model.Pesquisador;

public class PesquisadorJPA_DAO extends GenericJPADAO<Pesquisador> implements PesquisadorDAO{
	
	public PesquisadorJPA_DAO() {
		super(Pesquisador.class);
	}
}
