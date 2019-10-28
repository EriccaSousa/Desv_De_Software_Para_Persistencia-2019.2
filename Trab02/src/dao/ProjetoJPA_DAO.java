package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Projeto;

public class ProjetoJPA_DAO extends GenericJPADAO<Projeto> implements ProjetoDAO {

	public ProjetoJPA_DAO() {
		super(Projeto.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("Projeto.findByNome");

		query.setParameter(1, nome);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> findById(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("SELECT p FROM Projeto WHERE p.projeto_id LIKE :id");

		query.setParameter("id", id);

		return query.getResultList();
	}
}
