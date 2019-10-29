package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dao.SecretarioDAO;
import dao.SecretarioJPA_DAO;
import model.Secretario;

public class SecretarioCRUD {
	static Scanner read = new Scanner(System.in);

	public static void criarSecretario() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();

		try {
			secretarioDAO.beginTransaction();

			System.out.println("Informe os dados para Secretário(a)----------\nNúmero de identificação: ");
			String id = read.nextLine();
			System.out.println("Nome: ");
			String nome = read.nextLine();
			System.out.println("Endereço: ");
			String endereco = read.nextLine();
			System.out.println("Sexo: ");
			String sexo = read.nextLine();
			System.out.println("Data Aniversário: ");
			String dataAniver = read.nextLine();
			System.out.println("Sálario: ");
			double salario = read.nextDouble();
			read.nextLine();
			System.out.println("Grau de Escolaridade: ");
			String grauEscolar = read.nextLine();

			secretarioDAO.save(new Secretario(id, nome, endereco, sexo, dataAniver, salario, null, grauEscolar));
			secretarioDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Secretário(a)!\n");

			secretarioDAO.rollback();
			e.printStackTrace();
		} finally {
			secretarioDAO.close();
		}
		System.out.println("\nSecretário(a) salvo com sucesso!\n");
	}

	public static void findAll() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();

		List<Secretario> secretarios = secretarioDAO.findAll();
		secretarioDAO.close();

		System.out.println("\n");
		for (Secretario secretario : secretarios) {
			System.out.println(secretario);
		}
		System.out.println("\n");
	}

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
			System.out.println("Erro!");
			e.printStackTrace();
		}

		return secretario;
	}

	public static Secretario findById() {
		Secretario secretario = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o numero de identificação: ");
		String id = read.nextLine();

		try {
			secretario = (Secretario) em.createQuery("SELECT s FROM Secretario s WHERE s.id LIKE :id")
					.setParameter("id", id + "%").getSingleResult();
			em.close();
		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
		}

		return secretario;
	}

	public static void deleteByNome() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();

		try {
			secretarioDAO.beginTransaction();

			secretarioDAO.delete(findByNome());

			secretarioDAO.close();
			System.out.println("Secretário deletado com sucesso!");
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

	public static void deleteById() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();

		try {
			secretarioDAO.beginTransaction();

			secretarioDAO.delete(findById());

			secretarioDAO.close();
			System.out.println("Secretário deletado com sucesso!");
			secretarioDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("Erro!");
			secretarioDAO.rollback();
			e.printStackTrace();
		} finally {
			secretarioDAO.close();
		}
		System.out.println("===========================================");
	}

}
