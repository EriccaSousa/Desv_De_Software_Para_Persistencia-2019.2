package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Dependente;

public class DependenteJPA_DAO extends GenericJPADAO<Dependente> implements DependenteDAO {

	public DependenteJPA_DAO() {
		super(Dependente.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dependente> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("Dependente.findByNome");

		query.setParameter(1, nome);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dependente> findById(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("SELECT d FROM Dependente d WHERE d.dependente_id LIKE :id");

		query.setParameter("id", id);

		return query.getResultList();
	}

}
