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

			System.out.println("Informe os dados para Dependente\nNúmero de identificação: ");
			String id = read.nextLine();
			System.out.println("Nome: ");
			String nome = read.nextLine();
			System.out.println("Data Aniversário: ");
			String dataAniver = read.nextLine();
			System.out.println("Grau de parentesco: ");
			String parentesco = read.nextLine();

			Funcionario parente = FuncionarioDependente();

			dependenteDAO.save(new Dependente(id, nome, dataAniver, parentesco, parente));
			dependenteDAO.commit();
			System.out.println(parente);
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
			funcionario = null;
		} else if (opcao == 2) {
			funcionario = SecretarioCRUD.findById();
		} else if (opcao == 3) {
			funcionario = null;
		} else {
			System.out.println("Opção inválida!");
		}

		return funcionario;
	}

	public static void findAll() {
		DependenteDAO dependenteDAO = new DependenteJPA_DAO();

		List<Dependente> dependentes = dependenteDAO.findAll();
		dependenteDAO.close();

		System.out.println("\n");
		for (Dependente dependente : dependentes) {
			System.out.println(dependente);
		}
		System.out.println("\n");
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
			System.out.println("Erro!");
			e.printStackTrace();
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
			dependente = (Dependente) em.createQuery("SELECT d FROM Dependente d WHERE d.id LIKE :id")
					.setParameter("id", id + "%").getSingleResult();
			em.close();
		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
		}

		return dependente;
	}

	public static void deleteByNome() {
		DependenteDAO dependenteDAO = new DependenteJPA_DAO();

		try {
			dependenteDAO.beginTransaction();

			dependenteDAO.delete(findByNome());

			dependenteDAO.close();
			System.out.println("Dependente deletado com sucesso!");
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

	public static void deleteById() {
		DependenteDAO dependenteDAO = new DependenteJPA_DAO();

		try {
			dependenteDAO.beginTransaction();

			dependenteDAO.delete(findById());

			dependenteDAO.close();
			System.out.println("Dependente deletado com sucesso!");

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