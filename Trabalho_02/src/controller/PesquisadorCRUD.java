package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dao.PesquisadorDAO;
import dao.PesquisadorJPA_DAO;
import model.Pesquisador;

public class PesquisadorCRUD {

	static Scanner read = new Scanner(System.in);

	public static void findAll() {
		PesquisadorDAO pesquisadorDAO = new PesquisadorJPA_DAO();

		List<Pesquisador> pesquisadores = pesquisadorDAO.findAll();
		pesquisadorDAO.close();
		if (pesquisadores == null) {
			System.out.println("N�o existe nenhum pesquisador cadastrado no banco");
			return;
		}
		for (Pesquisador pesquisador : pesquisadores) {
			System.out.println(pesquisador);
		}
		System.out.println("===========================================");
	}

	public static Pesquisador findByNome() {
		Pesquisador pesquisadorTemp = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();

		try {
			pesquisadorTemp = (Pesquisador) em.createQuery("SELECT p FROM Pesquisador p WHERE p.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (pesquisadorTemp == null) {
				System.out.println("Pesquisador n�o existe no banco ou n�o foi encontrado!");
			}
			e.printStackTrace();
		}

		return pesquisadorTemp;
	}

	public static void deleteByNome() {
		PesquisadorDAO pesquisadorDAO = new PesquisadorJPA_DAO();
		PesquisadorDAO pesquisadorDAO2 = new PesquisadorJPA_DAO();

		try {
			pesquisadorDAO.beginTransaction();

			Pesquisador pesquisador = findByNome();
			pesquisador.setDepartamento(null);

			pesquisadorDAO.delete(pesquisador);

			pesquisadorDAO.close();

			pesquisadorDAO2.beginTransaction();

			System.out.println("Confirme o nome do Dependente a ser removido.");
			Pesquisador pesquisador2 = findByNome();

			pesquisador2.setDepartamento(null);

			pesquisadorDAO2.delete(pesquisador);

			pesquisadorDAO2.close();
			System.out.println("Projeto deletado com sucesso!");
			pesquisadorDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("Erro!");
			pesquisadorDAO.rollback();
			e.printStackTrace();
		} finally {
			pesquisadorDAO.close();
		}

		System.out.println("\n");
	}

}
