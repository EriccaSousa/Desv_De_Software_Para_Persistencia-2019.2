package dao;

import model.Projeto;

public class ProjetoJPA_DAO extends GenericJPADAO<Projeto> implements ProjetoDAO {

	public ProjetoJPA_DAO() {
		super(Projeto.class);
	}
}
