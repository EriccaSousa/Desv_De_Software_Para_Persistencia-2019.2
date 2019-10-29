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

	/*
	 * Criando Departamentos.
	 */
	public static void criarDepartamento() {

		System.out.println("Informe os dados para Departamento");
		System.out.println("Nome: ");
		String nome = read.nextLine();

		Departamento departamento = new Departamento(nome, null);

		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();
		try {
			departamentoDAO.beginTransaction();
			departamentoDAO.save(departamento);
			departamentoDAO.commit();

			System.out.println("\nDepartamento salvo com sucesso!\n");
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Secretário(a)!\n");

			departamentoDAO.rollback();
			e.printStackTrace();
		} finally {
			departamentoDAO.close();
		}
	}

	/*
	 * Buscando todos os Departamentos;
	 */

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

	/*
	 * Buscando todos os departamento por nome;
	 */
	public static void findByNome() {
		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();

		List<Departamento> departamentos = departamentoDAO.findByNome(nome);

		departamentoDAO.close();

		System.out.println("\n");
		for (Departamento departamento : departamentos) {
			System.out.println(departamento);
		}
		System.out.println("\n");
	}

	/*
	 * Buscando todos os departamento por id;
	 */
	public static void findById1() {
		DepartamentoDAO departamentoDAO = new DepartamentoJPA_DAO();

		System.out.println("Informe o número de identificação: ");
		int id = read.nextInt();

		List<Departamento> departamentos = departamentoDAO.findById(id);

		departamentoDAO.close();

		System.out.println("\n");
		for (Departamento departamento : departamentos) {
			System.out.println(departamento);
		}
		System.out.println("\n");
	}

	/*
	 * Buscando departamento por id, e retornando. Essa função é usada para
	 * cadastrar Funcionário e Projeto e tambem para remoçoes de Departamento.
	 */
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
	/*
	 * Deletando Departamentos por id;
	 */

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
