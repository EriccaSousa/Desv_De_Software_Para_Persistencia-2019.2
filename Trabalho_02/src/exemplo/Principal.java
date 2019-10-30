package exemplo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class Principal {

	public static void main(String[] args) {

		inserirDados();
		// buscarComJPQL();
		// buscarComCriteriaQuery();
		// buscarComNamedQuery();
		// buscarComNativeQuery();
		// buscarComNativeQuery2();
		// inserirDadosComJPAUtil();
		// inserirDadosComDAO();
		// deleteComDAO();
		// buscarComDAO();
		System.out.println("FIM - JPA");
		
		/*
		 * EXERCÍCIO: FAÇA UM MÉTODO findByNome PARA BUSCAR
		 * UM CONTATO (OU MAIS) POR NOME. PENSE ONDE COLOCAR
		 * ESSE MÉTODO (ONDE FAZ SENTIDO ELE ESTAR).
		*/
	}
	
	public static void buscarComDAO() {
		ContatoDAO cDao = new ContatoJPADAO();
		List<Contato> contatos = cDao.findAll();
		cDao.close();
		for(Contato contato : contatos) {
			System.out.println(contato);
		}
		System.out.println("===========================================");
	}
	
	public static void deleteComDAO() {
		ContatoDAO cDao = new ContatoJPADAO();
		try {
			cDao.beginTransaction();
			
			// Contato c = cDao.find(4);
			/* Contato c = new Contato();
			c.setId(11);
			cDao.delete(c);*/	
			cDao.deleteById(10);
			cDao.close();
			System.out.println("===========================================");
			// System.out.println(c);
			cDao.commit();
		} catch(IllegalStateException | PersistenceException e) {
			cDao.rollback();
			e.printStackTrace();
		} finally {
			cDao.close();
		}
	}
	
	public static void inserirDadosComDAO() {
		ContatoDAO cDao = new ContatoJPADAO();
		
		
		try {
			cDao.beginTransaction();
			cDao.save(new Contato("Ronaldo", "999"));
			cDao.save(new Contato("Mary", "100"));
			cDao.save(new Contato(1, "Junior", "300"));
			cDao.commit();
		} catch(IllegalStateException | PersistenceException e) {
			cDao.rollback();
			e.printStackTrace();
		} finally {
			cDao.close();
		}
		System.out.println("===========================================");
	}
	
	public static void inserirDadosComJPAUtil() {
		
		EntityManager em = JPAUtil.getEntityManager(); 
		try {
			JPAUtil.beginTransaction();
			
			em.persist(new Contato("Italo", "555"));
			em.persist(new Contato("Victor", "666"));
			em.persist(new Contato("Fernanda", "777"));
			em.persist(new Contato("Evenilson", "888"));
			
			JPAUtil.commit();
		} catch(IllegalStateException | PersistenceException e) {
			JPAUtil.rollback();
			e.printStackTrace();
		} finally {
			JPAUtil.closeEntityManager();
		}


		System.out.println("===========================================");
	}
	
	@SuppressWarnings("unchecked")
	public static void buscarComNativeQuery2() {


		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		Query query 
			= em.createNativeQuery("SELECT * FROM contato", Contato.class);
		List<Contato> contatos = query.getResultList();
		
		em.close();
		System.out.println("===========================================");
		for(Contato contato : contatos) {
			System.out.println(contato);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void buscarComNativeQuery() {


		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		Query query = em.createNativeQuery("SELECT * FROM contato");
		List<Object[]> result = query.getResultList();
		List<Contato> contatos = new ArrayList<Contato>();
		for(Object[] linha : result) {
			int id = (int) linha[0];
			String nome = (String) linha[1];
			String telefone = (String) linha[2];
			contatos.add(new Contato(id, nome, telefone));
		}
		em.close();
		System.out.println("===========================================");
		for(Contato contato : contatos) {
			System.out.println(contato);
		}
	}

	public static void buscarComNamedQuery() {


		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		List<Contato> contatos = em.createNamedQuery("Contato.findAll", 
										  Contato.class).getResultList();
		em.close();
		System.out.println("===========================================");
		for(Contato contato : contatos) {
			System.out.println(contato);
		}
	}

	public static void buscarComCriteriaQuery() {


		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contato> cq = cb.createQuery(Contato.class);
		cq.from(Contato.class);
		List<Contato> contatos = em.createQuery(cq).getResultList();
		em.close();
		System.out.println("===========================================");
		for(Contato contato : contatos) {
			System.out.println(contato);
		}
	}

	public static void buscarComJPQL() {


		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		List<Contato> contatos = 
				em.createQuery("from Contato", Contato.class).getResultList();
		em.close();
		System.out.println("===========================================");
		for(Contato contato : contatos) {
			System.out.println(contato);
		}
		
	}

	public static void inserirDados() {
		Contato c = new Contato("Jean", "123");

		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(c);
			/*
			em.persist(new Contato("Sting", "111"));
			em.persist(new Contato("Ronier", "222"));
			em.persist(new Contato("Lucas", "333"));
			em.persist(new Contato("Wesley", "444"));
			 */
			tx.commit();
		} catch(IllegalStateException | PersistenceException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}


		System.out.println("===========================================");

		System.out.println(c);
	}
}
