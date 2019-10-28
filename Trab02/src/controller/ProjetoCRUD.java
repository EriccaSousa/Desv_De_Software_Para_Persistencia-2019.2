package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dao.DepartamentoDAO;
import dao.DepartamentoJPA_DAO;
import dao.ProjetoDAO;
import dao.ProjetoJPA_DAO;
import model.Departamento;
import model.Projeto;

public class ProjetoCRUD {
	static Scanner read = new Scanner(System.in);

	public static void criarProjeto() {
		ProjetoDAO projetoDAO = new ProjetoJPA_DAO();

		try {
			projetoDAO.beginTransaction();

			System.out.println("Informe os dados para Projeto\n");
			System.out.println("Nome: ");
			String nome = read.nextLine();
			System.out.println("Período: ");
			String periodo = read.nextLine();

			Departamento departamento = DepartamentoCRUD.findById();

			projetoDAO.save(new Projeto(nome, periodo, departamento));
			projetoDAO.commit();

			System.out.println("\nProjeto salvo com sucesso!\n");
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Projeto!\n");

			projetoDAO.rollback();
			e.printStackTrace();
		} finally {
			projetoDAO.close();
		}

	}

	/*
	 * Buscando todos os Projetos;
	 */
	public static void findAll() {
		ProjetoDAO projetoDAO = new ProjetoJPA_DAO();

		List<Projeto> projetos = projetoDAO.findAll();
		projetoDAO.close();

		System.out.println("\n");
		for (Projeto projeto : projetos) {
			System.out.println(projeto);
		}
		System.out.println("\n");
	}

	/*
	 * Buscando todos os Projetos por nome;
	 */
	public static void findByNome() {
		ProjetoDAO projetoDAO = new ProjetoJPA_DAO();

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();

		List<Projeto> projetos = projetoDAO.findByNome(nome);

		projetoDAO.close();

		System.out.println("\n");
		for (Projeto projeto : projetos) {
			System.out.println(projeto);
		}
		System.out.println("\n");
	}

	/*
	 * Buscando todos os Projetos por id;
	 */
	public static void findById1() {
		ProjetoDAO projetoDAO = new ProjetoJPA_DAO();

		System.out.println("Informe o número de identificação: ");
		int id = read.nextInt();

		List<Projeto> projetos = projetoDAO.findById(id);

		projetoDAO.close();

		System.out.println("\n");
		for (Projeto projeto : projetos) {
			System.out.println(projeto);
		}
		System.out.println("\n");
	}

	/*
	 * Buscando departamento por id, e retornando.
	 */
	public static Projeto findById() {
		Projeto projeto = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o numero de identificação: ");
		String id = read.nextLine();

		try {
			projeto = (Projeto) em.createQuery("SELECT p FROM Projeto p WHERE p.id LIKE :id")
					.setParameter("id", id + "%").getSingleResult();
			em.close();
		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
		}

		return projeto;
	}

	public static void deleteById() {
		ProjetoDAO projetoDAO = new ProjetoJPA_DAO();
		try {
			projetoDAO.beginTransaction();

			projetoDAO.delete(findById());

			projetoDAO.close();
			System.out.println("Projeto deletado com sucesso!");

			projetoDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("Erro!");
			projetoDAO.rollback();
			e.printStackTrace();
		} finally {
			projetoDAO.close();
		}
		System.out.println("\n");
	}

}
