package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dao.DependenteDAO;
import dao.DependenteJPA_DAO;
import model.Dependente;
import model.Funcionario;

public class DependenteCRUD {

	static Scanner read = new Scanner(System.in);

	public static void criarDependente() {

		DependenteDAO dependenteDAO = new DependenteJPA_DAO();

		try {
			dependenteDAO.beginTransaction();

			System.out.println("Informe os dados para Dependente: ");
			System.out.println("Nome: ");
			String nome = read.nextLine();
			System.out.println("Data Aniversário: ");
			String dataAniver = read.nextLine();
			System.out.println("Grau de parentesco: ");
			String parentesco = read.nextLine();

			Funcionario funcionario = FuncionarioDependente();

			dependenteDAO.save(new Dependente(nome, dataAniver, parentesco, funcionario));
			dependenteDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Dependente!\n");

			dependenteDAO.rollback();
			e.printStackTrace();
		} finally {
			dependenteDAO.close();
		}

	}

	public static Funcionario FuncionarioDependente() {
		Funcionario funcionario = null;

		System.out.println(
				"Informe a função do seu agregado:\n[ 1 ] Funcionário de Limpeza\n[ 2 ] Secretário(a)\n[ 3 ] Pesquisador(a)");
		int opcao = read.nextInt();

		if (opcao == 1) {
			funcionario = FuncLimpezaCRUD.findByNome();
		} else if (opcao == 2) {
			funcionario = SecretarioCRUD.findByNome();
		} else if (opcao == 3) {
			funcionario = PesquisadorCRUD.findByNome();
		} else {
			System.out.println("Opção inválida!");
		}

		return funcionario;
	}

	public static void findAll() {
		try {
			DependenteDAO dependenteDAO = new DependenteJPA_DAO();

			List<Dependente> dependentes = dependenteDAO.findAll();
			dependenteDAO.close();

			if(dependentes == null) {
				System.out.println("N�o existe nenhum dependente foi cadastrado no banco");
				return;
			}
			System.out.println("\n");
			for (Dependente dependente : dependentes) {
				System.out.println(dependente);
			}
			System.out.println("\n");
		} catch (Exception e) {
			System.out.println("\nNão há Dependentes cadastrados.\n");
		}
	}

	public static Dependente findByNome() {
		Dependente dependente = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();

		try {
			dependente = (Dependente) em.createQuery("SELECT d FROM Dependente d WHERE d.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (dependente == null) {
				System.out.println("\nO nome informado não corresponde a nenhum Dependente cadastrado no sistema.\n");
			}
		}

		return dependente;
	}

	public static Dependente findById() {
		Dependente dependente = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o numero de identificação: ");
		String id = read.nextLine();

		try {
			dependente = (Dependente) em.createQuery("SELECT d FROM Dependente d WHERE d.dependente_id LIKE :id")
					.setParameter("id", id + "%").getSingleResult();
			em.close();
		} catch (Exception e) {
			System.out.println("\nO nome informado não corresponde a nenhum Dependente cadastrado no sistema.\n");

		}

		return dependente;
	}

	public static void deleteByNome() {
		DependenteDAO dependenteDAO = new DependenteJPA_DAO();
		DependenteDAO dependenteDAO2 = new DependenteJPA_DAO();

		try {
			dependenteDAO.beginTransaction();

			Dependente dependente = findByNome();

			dependente.setFuncionario(null);

			dependenteDAO.delete(dependente);

			dependenteDAO.close();

			dependenteDAO2.beginTransaction();

			System.out.println("Confirme o nome do Dependente a ser removido.");
			Dependente dependente2 = findByNome();

			dependente2.setFuncionario(null);

			dependenteDAO2.delete(dependente);

			dependenteDAO2.close();
			System.out.println("Projeto deletado com sucesso!");
			dependenteDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("Erro!");
			dependenteDAO.rollback();
			e.printStackTrace();
		} finally {
			dependenteDAO.close();
		}

		System.out.println("\n");
	}

}
