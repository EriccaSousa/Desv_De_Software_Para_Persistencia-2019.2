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
import util.VerificacoesUtil;

public class DepartamentoCRUD {
	static Scanner read = new Scanner(System.in);

	public static void criarDepartamento() {

		System.out.println("Informe os dados para Departamento");
		System.out.println("Nome do Departamento: ");
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
			System.out.println(
					"\nJá existe um Departamento com esse nome cadastrado no sistema.\nPor favor, escolha outro nome.");
		}

	}

	public static void findAll() {
		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

		List<Departamento> departamentos = departamentoDAO.findAll();
		departamentoDAO.close();

		System.out.println("\n");
		for (Departamento departamento : departamentos) {
			System.out.println(departamento);
		}
		System.out.println("\n");
	}

	public static Departamento findByNome() {
		Departamento departamento = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o nome do Departamento: ");
		String nome = read.nextLine();

		try {
			departamento = (Departamento) em.createQuery("SELECT d FROM Departamento d WHERE d.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();

		} catch (Exception e) {
			if (departamento == null) {
				System.out.println("\nO nome informado não corresponde a nenhum Departamento cadastrado no sistema.\n");
			}
		}

		return departamento;
	}

	public static Departamento findByNome(String nome) {
		Departamento departamento = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		try {
			departamento = (Departamento) em.createQuery("SELECT d FROM Departamento d WHERE d.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if (departamento == null) {
				System.out.println("\nNome aceito.\n");
			}
		}

		return departamento;
	}

	public static Departamento findById() {
		Departamento departamento = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o numero de identificação: ");
		int id = read.nextInt();

		try {
			departamento = (Departamento) em
					.createQuery("SELECT dp FROM Departamento d WHERE d.departamento_id LIKE :id")
					.setParameter("id", id).getSingleResult();
			em.close();

		} catch (Exception e) {
			if (departamento == null) {
				System.out.println("\nO id informado não corresponde a nenhum Departamento cadastrado no sistema.\n");
			}
		}

		return departamento;
	}

	public static void deleteByNome() {
		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

		try {
			departamentoDAO.beginTransaction();

			departamentoDAO.delete(findByNome());

			departamentoDAO.close();
			System.out.println("Departamento deletado com sucesso!");
			departamentoDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("Erro!");
			departamentoDAO.rollback();
			e.printStackTrace();
		} finally {
			departamentoDAO.close();
		}
		System.out.println("\n");
	}

	public static void deleteById() {
		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

		try {
			departamentoDAO.beginTransaction();

			departamentoDAO.delete(findById());

			departamentoDAO.close();
			System.out.println("Departamento deletado com sucesso!");
			departamentoDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("Erro!");
			departamentoDAO.rollback();
			e.printStackTrace();
		} finally {
			departamentoDAO.close();
		}
		System.out.println("\n");
	}

}
