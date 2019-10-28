package dao;

import model.Departamento;

public class DepartamentoJPA_DAO extends GenericJPADAO<Departamento> implements DepartamentoDAO {

	public DepartamentoJPA_DAO() {
		super(Departamento.class);
	}

}
