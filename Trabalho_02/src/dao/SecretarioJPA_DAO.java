package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Funcionario;
import model.Secretario;

public class SecretarioJPA_DAO extends GenericJPADAO<Secretario> implements SecretarioDAO {

	public SecretarioJPA_DAO() {
		super(Secretario.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Secretario> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("Secretario.findByNome");

		query.setParameter(1, nome);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Secretario> findById(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("SELECT s FROM Scretario s WHERE s.secretario_id LIKE :id");

		query.setParameter("id", id);

		return query.getResultList();
	}

	@Override
	public void save(Funcionario funcionario) {
		JPAUtil.getEntityManager().merge(funcionario);

	}

}
