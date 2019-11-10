package br.ufc.qxd.persist.conteudo_7_jpa.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufc.qxd.persist.conteudo_7_jpa.dao.ContatoDAO;
import br.ufc.qxd.persist.conteudo_7_jpa.jpa.JPAUtil;
import br.ufc.qxd.persist.conteudo_7_jpa.model.Contato;

public class ContatoJPADAO extends GenericJPADAO<Contato>
						   implements ContatoDAO {

	
	
	public ContatoJPADAO() {
		super(Contato.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contato> findByNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("Contato.findByNome");
		// query.setParameter("nome", nome);
		query.setParameter(1, nome);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contato> findByParteDoNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("SELECT c FROM Contato c"
									+ " WHERE c.nome LIKE :nome");
		query.setParameter("nome", nome+"%");
		return query.getResultList();
	}
	
}








