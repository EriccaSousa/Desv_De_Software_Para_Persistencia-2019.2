package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Funcionario;

public class FuncionarioJPA_DAO extends GenericJPADAO<Funcionario> implements FuncionarioDAO {

	public FuncionarioJPA_DAO() {
		super(Funcionario.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("Funcionario.findByNome");

		query.setParameter(1, nome);

		return query.getResultList();
	}

	@Override
	public List<Funcionario> findById(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("SELECT f FROM Funcionario f WHERE f.funcionario_id LIKE :id");

		query.setParameter("id", id);

		return query.getResultList();
	}

}
