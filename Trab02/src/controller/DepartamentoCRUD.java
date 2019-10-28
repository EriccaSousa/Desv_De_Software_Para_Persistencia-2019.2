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

public class DepartamentoCRUD {
	static Scanner read = new Scanner(System.in);

	public static void criarDepartamento() {
		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

		try {
			departamentoDAO.beginTransaction();

			System.out.println("Informe os dados para Departamento+----------\nNúmero de identificação: ");
			String id = read.nextLine();
			System.out.println("Nome: ");
			String nome = read.nextLine();

			departamentoDAO.save(new Departamento(id, nome, null));
			departamentoDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Secretário(a)!\n");

			departamentoDAO.rollback();
			e.printStackTrace();
		} finally {
			departamentoDAO.close();
		}
		System.out.println("\nDepartamento salvo com sucesso!\n");
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

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();

		try {
			departamento = (Departamento) em.createQuery("SELECT dp FROM Departamento dp WHERE dp.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
		}

		return departamento;
	}

	public static Departamento findById() {
		Departamento departamento = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o numero de identificação: ");
		String id = read.nextLine();

		try {
			departamento = (Departamento) em.createQuery("SELECT dp FROM Departamento dp WHERE dp.id LIKE :id")
					.setParameter("id", id + "%").getSingleResult();
			em.close();
		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
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
