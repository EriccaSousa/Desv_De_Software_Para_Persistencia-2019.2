package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dao.PesquisadorDAO;
import dao.PesquisadorJPA_DAO;
import dao.SecretarioDAO;
import dao.SecretarioJPA_DAO;
import model.Pesquisador;
import model.Secretario;

public class SecretarioCRUD {
	static Scanner read = new Scanner(System.in);

	public static Secretario findByNome() {
		Secretario secretario = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();

		try {
			secretario = (Secretario) em.createQuery("SELECT s FROM Secretario s WHERE s.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (secretario == null) {
				System.out.println("N�o existe secretario cadastrado no banco ou n�o foi encontrado");
			}
			e.printStackTrace();
		}

		return secretario;
	}

	public static void findAll() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();

		List<Secretario> secretarios = secretarioDAO.findAll();
		secretarioDAO.close();
		if (secretarios == null) {
			System.out.println("N�o existe nenhum secret�rio cadastrado no banco");
			return;
		}
		System.out.println("\n");
		for (Secretario secretario : secretarios) {
			System.out.println(secretario);
		}
		System.out.println("\n");
	}

	public static void deleteByNome() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();
		SecretarioDAO secretarioDAO2 = new SecretarioJPA_DAO();

		try {
			secretarioDAO.beginTransaction();

			Secretario secretario = findByNome();
			secretario.setDepartamento(null);

			secretarioDAO.delete(secretario);

			secretarioDAO.close();

			secretarioDAO2.beginTransaction();

			System.out.println("Confirme o nome do Secretario a ser removido.");
			Secretario secretario2 = findByNome();

			secretario2.setDepartamento(null);

			secretarioDAO2.delete(secretario);

			secretarioDAO2.close();
			System.out.println("Secretario deletado com sucesso!");
			secretarioDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("Erro!");
			secretarioDAO.rollback();
			e.printStackTrace();
		} finally {
			secretarioDAO.close();
		}

		System.out.println("\n");
	}

}
