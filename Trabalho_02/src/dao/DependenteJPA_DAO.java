package dao;


import model.Dependente;

public class DependenteJPA_DAO extends GenericJPADAO<Dependente> implements DependenteDAO {

	public DependenteJPA_DAO() {
		super(Dependente.class);
	}
}
