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
import util.TextosUtil;
import util.VerificacoesUtil;

public class DependenteCRUD {

	static Scanner read = new Scanner(System.in);

	public static void criarDependente() {

		DependenteDAO dependenteDAO = new DependenteJPA_DAO();

		try {
			dependenteDAO.beginTransaction();

			TextosUtil.demarcacao();
			System.out.println("\t   CADASTRO DE DEPENDENTES");
			TextosUtil.demarcacao();

			System.out.println("Informe os dados para Dependente: ");

			Funcionario funcionario = FuncionarioDependente();

			if (funcionario == null) {
				System.out.println("\no Funcionário informado não existe no sistema.\n");
			} else {
				System.out.print("Nome: ");
				String nome = read.nextLine();
				System.out.print("Data Aniversário: ");
				String dataAniver = read.nextLine();
				System.out.print("Grau de parentesco: ");
				String parentesco = read.nextLine();

				dependenteDAO.save(new Dependente(nome, dataAniver, parentesco, funcionario));
				dependenteDAO.commit();
			}
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Dependente!\n");

			dependenteDAO.rollback();
			e.printStackTrace();
		} finally {
			dependenteDAO.close();
		}
		TextosUtil.demarcacao();
	}

	public static Funcionario FuncionarioDependente() {
		Funcionario funcionario = null;

		System.out.println(
				"\nFunção do funcionário agregado:\n[ 1 ] Funcionário de Limpeza\n[ 2 ] Secretário(a)\n[ 3 ] Pesquisador(a)");
		System.out.print("Opcao: ");
		int opcao = read.nextInt();
		read.nextLine();

		if (opcao == 1) {
			funcionario = FuncLimpezaCRUD.findByNome();
		} else if (opcao == 2) {
			funcionario = SecretarioCRUD.findByNome();
		} else if (opcao == 3) {
			funcionario = PesquisadorCRUD.findByNome();
		} else {
			TextosUtil.textoDefault();
			TextosUtil.demarcacao();
			TextosUtil.demarcacao();
		}

		return funcionario;
	}

	public static void findAll() {

		DependenteDAO dependenteDAO = new DependenteJPA_DAO();

		List<Dependente> dependentes = dependenteDAO.findAll();

		dependenteDAO.close();

		TextosUtil.demarcacao();
		for (Dependente dependente : dependentes) {
			System.out.println("Dependente");
			System.out.println(dependente);
		}
		TextosUtil.demarcacao();
	}

	public static Dependente findByNome() {
		Dependente dependente = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		TextosUtil.demarcacao();
		System.out.println("Nome do Dependente: ");
		String nome = read.nextLine();

		try {
			dependente = (Dependente) em.createQuery("SELECT d FROM Dependente d WHERE d.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (dependente == null) {
				TextosUtil.demarcacao();
				TextosUtil.nomeNaoEncontrado();
			}
		}
		TextosUtil.demarcacao();
		return dependente;
	}

	// Função auxiliar
	public static Dependente findByNome(String nome) {
		Dependente dependente = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		try {
			dependente = (Dependente) em.createQuery("SELECT d FROM Dependente d WHERE d.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (dependente == null) {
				TextosUtil.demarcacao();
				TextosUtil.nomeNaoEncontrado();
			}
		}
		return dependente;
	}

	public static void deleteByNome() {
		DependenteDAO dependenteDAO = new DependenteJPA_DAO();
		DependenteDAO dependenteDAO2 = new DependenteJPA_DAO();

		TextosUtil.demarcacao();
		System.out.print("Informe o nome do Dependente a ser deletado: ");
		String nome = read.nextLine();

		if (VerificacoesUtil.verificaExistenciaDependente(nome) == false) {
			try {
				dependenteDAO.beginTransaction();

				System.out.print("Confirme o nome do Dependente: ");
				Dependente dependente = findByNome();

				dependente.setFuncionario(null);

				dependenteDAO.delete(dependente);
				dependenteDAO.close();
				dependenteDAO.commit();

				dependenteDAO.beginTransaction();
				dependenteDAO.delete(dependente);
				dependenteDAO.close();

				dependenteDAO.commit();

				TextosUtil.deletadoComSucesso();
			} catch (IllegalStateException | PersistenceException e) {
				System.out.println("Erro!");
				dependenteDAO.rollback();
				e.printStackTrace();
			} finally {
				dependenteDAO.close();
			}
		} else {
			TextosUtil.demarcacao();
			TextosUtil.nomeNaoEncontrado();
		}

		TextosUtil.demarcacao();
	}

}
