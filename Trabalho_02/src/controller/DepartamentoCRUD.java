package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dao.DepartamentoDAO;
import dao.DepartamentoJPA_DAO;
import model.Departamento;
import util.TextosUtil;
import util.VerificacoesUtil;

public class DepartamentoCRUD {
	static Scanner read = new Scanner(System.in);

	public static void criarDepartamento() {

		TextosUtil.demarcacao();
		System.out.println("\t   CADASTRO DE DEPARTAMENTO");
		TextosUtil.demarcacao();
		System.out.println("\n\tInforme os dados para Departamento\n");
		System.out.print("Nome: ");
		String nome = read.nextLine();

		if (VerificacoesUtil.verificaExistenciaDepartamento(nome) == true) {
			Departamento departamento = new Departamento(nome, null, null);

			DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

			try {
				departamentoDAO.beginTransaction();
				departamentoDAO.save(departamento);
				departamentoDAO.commit();

				System.out.println("\nDepartamento salvo com sucesso!\n");
			} catch (IllegalStateException | PersistenceException e) {
				System.out.println("\nErro ao salvar Departamento(a)!\n");

				departamentoDAO.rollback();
				e.printStackTrace();
			} finally {
				departamentoDAO.close();
			}

		} else {
			TextosUtil.demarcacao();
			TextosUtil.nomeJaCadastrado();
		}
		TextosUtil.demarcacao();
	}

	public static void findAll() {
		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

		List<Departamento> departamentos = departamentoDAO.findAll();
		departamentoDAO.close();

		TextosUtil.demarcacao();
		for (Departamento departamento : departamentos) {
			System.out.println(departamento);
		}
		TextosUtil.demarcacao();
	}

	public static Departamento findByNome() {
		Departamento departamento = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		TextosUtil.demarcacao();
		System.out.print("\nNome do Departamento: ");
		String nome = read.nextLine();

		try {
			departamento = (Departamento) em.createQuery("SELECT d FROM Departamento d WHERE d.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();

		} catch (Exception e) {
			if (departamento == null) {
				TextosUtil.demarcacao();
				TextosUtil.nomeNaoEncontrado();
			}
		}
		TextosUtil.demarcacao();
		return departamento;
	}

	// Função auxiliar
	public static Departamento findByNome(String nome) {
		Departamento departamento = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		try {
			departamento = (Departamento) em.createQuery("SELECT d FROM Departamento d WHERE d.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (departamento == null) {
				TextosUtil.demarcacao();
				TextosUtil.nomeNaoEncontrado();
			}
		}

		return departamento;
	}

	public static void deleteByNome() {
		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

		TextosUtil.demarcacao();
		System.out.print("Informe o nome do Departamento a ser deletado: ");
		String nome = read.nextLine();

		if (VerificacoesUtil.verificaExistenciaDepartamento(nome) == false) {
			try {
				departamentoDAO.beginTransaction();

				System.out.print("Confirme o nome do Departamento: ");
				departamentoDAO.delete(findByNome());

				departamentoDAO.close();

				TextosUtil.demarcacao();
				TextosUtil.deletadoComSucesso();

				departamentoDAO.commit();
			} catch (IllegalStateException | PersistenceException e) {
				System.out.println("Erro!");
				departamentoDAO.rollback();
				e.printStackTrace();
			} finally {
				departamentoDAO.close();
			}
		} else {
			TextosUtil.demarcacao();
			TextosUtil.nomeNaoEncontrado();
		}

		TextosUtil.demarcacao();
	}

}
