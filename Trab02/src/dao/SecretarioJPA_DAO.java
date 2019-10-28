package dao;

import model.Secretario;

public class SecretarioJPA_DAO extends GenericJPADAO<Secretario> implements SecretarioDAO {

	public SecretarioJPA_DAO() {
		super(Secretario.class);
	}

}
