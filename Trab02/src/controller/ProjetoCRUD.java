package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dao.ProjetoDAO;
import dao.ProjetoJPA_DAO;
import model.Departamento;
import model.Projeto;
import util.VerificacoesUtil;

public class ProjetoCRUD {
	static Scanner read = new Scanner(System.in);

	public static void criarProjeto() {

		System.out.println("Informe os dados para Projeto\n");
		System.out.println("Nome: ");
		String nome = read.nextLine();

		if (VerificacoesUtil.verificaExistenciaProjetos(nome) == true) {

			System.out.println("Informe o nome do Departamento respónsável: ");
			String nomeDep = read.nextLine();

			Departamento departamento = DepartamentoCRUD.findByNome(nomeDep);

			if (departamento != null) {
				System.out.println("Período: ");
				String periodo = read.nextLine();

				ProjetoDAO projetoDAO = new ProjetoJPA_DAO();

				try {
					projetoDAO.beginTransaction();

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

			} else {
				System.out.println("\nO Departamento informado não existe no sistema.\n");
			}

		} else {
			System.out.println(
					"\nJá existe um Projeto com esse nome cadastrado no sistema.\nPor favor, escolha outro nome.");
		}

	}

	public static void findAll() {
		try {
			ProjetoDAO projetoDAO = new ProjetoJPA_DAO();

			List<Projeto> projetos = projetoDAO.findAll();
			projetoDAO.close();

			System.out.println("\n");
			for (Projeto projeto : projetos) {
				System.out.println(projeto);
			}
			System.out.println("\n");
		} catch (Exception e) {
			System.out.println("\nNão há Projetos cadastrados.\n");
		}
	}

	public static Projeto findByNome() {
		Projeto projeto = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();

		try {
			projeto = (Projeto) em.createQuery("SELECT p FROM Projeto p WHERE p.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (projeto == null) {
				System.out.println("\nO nome informado não corresponde a nenhum Departamento cadastrado no sistema.\n");
			}
		}

		return projeto;
	}

	public static Projeto findByNome(String nome) {
		Projeto projeto = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		try {
			projeto = (Projeto) em.createQuery("SELECT p FROM Projeto p WHERE p.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (projeto == null) {
				System.out.println("\nNome aceito.\n");
			}
		}

		return projeto;
	}

	public static Projeto findById() {
		Projeto projeto = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o numero de identificação: ");
		String id = read.nextLine();

		try {
			projeto = (Projeto) em.createQuery("SELECT p FROM Projeto p WHERE p.id_projeto LIKE :id")
					.setParameter("id", id + "%").getSingleResult();
			em.close();
		} catch (Exception e) {
			System.out.println("\nO nome informado não corresponde a nenhum Departamento cadastrado no sistema.\n");

		}

		return projeto;
	}

	public static void deleteByNome() {
		ProjetoDAO projetoDAO = new ProjetoJPA_DAO();

		try {
			projetoDAO.beginTransaction();

			projetoDAO.delete(findByNome());

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
