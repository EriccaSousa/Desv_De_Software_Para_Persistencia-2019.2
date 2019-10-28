package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Departamento;

public class DepartamentoJPA_DAO extends GenericJPADAO<Departamento> implements DepartamentoDAO {

	public DepartamentoJPA_DAO() {
		super(Departamento.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departamento> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("Departamento.findByNome");

		query.setParameter(1, nome);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departamento> findById(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT d FROM Departamento d WHERE d.departamento_id LIKE :id");

		query.setParameter("id", id);

		return query.getResultList();
	}

}
