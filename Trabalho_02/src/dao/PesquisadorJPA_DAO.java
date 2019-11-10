package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Funcionario;
import model.Pesquisador;

public class PesquisadorJPA_DAO extends GenericJPADAO<Pesquisador> implements PesquisadorDAO {

	public PesquisadorJPA_DAO() {
		super(Pesquisador.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pesquisador> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("Pesquisador.findByNome");

		query.setParameter(1, nome);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pesquisador> findById(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("SELECT p FROM Pesquisador p WHERE p.pesquisador_id LIKE :id");

		query.setParameter("id", id);

		return query.getResultList();
	}

	@Override
	public void save(Funcionario funcionario) {
		JPAUtil.getEntityManager().merge(funcionario);

	}

}
