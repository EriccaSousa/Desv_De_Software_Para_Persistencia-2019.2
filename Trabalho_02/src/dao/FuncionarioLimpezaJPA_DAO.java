package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Funcionario;
import model.FuncionarioLimpeza;

public class FuncionarioLimpezaJPA_DAO extends GenericJPADAO<FuncionarioLimpeza> implements FuncionarioLimpezaDAO {

	public FuncionarioLimpezaJPA_DAO() {
		super(FuncionarioLimpeza.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FuncionarioLimpeza> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("FuncionarioLimpeza.findByNome");

		query.setParameter(1, nome);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FuncionarioLimpeza> findById(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em
				.createNamedQuery("SELECT fl FROM FuncionarioLimpeza fl WHERE fl.funcionarioLimpeza_id LIKE :id");

		query.setParameter("id", id);

		return query.getResultList();
	}

	@Override
	public void save(Funcionario funcionario) {
		JPAUtil.getEntityManager().merge(funcionario);

	}
}
