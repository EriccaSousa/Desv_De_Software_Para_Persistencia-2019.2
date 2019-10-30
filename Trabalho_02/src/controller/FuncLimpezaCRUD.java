package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.FuncionarioLimpezaDAO;
import dao.FuncionarioLimpezaJPA_DAO;
import model.FuncionarioLimpeza;

public class FuncLimpezaCRUD {
	static Scanner read = new Scanner(System.in);

	public static void findAll() {
		FuncionarioLimpezaDAO funcLimpezaDAO = new FuncionarioLimpezaJPA_DAO();

		List<FuncionarioLimpeza> funcionariosLimpeza = funcLimpezaDAO.findAll();
		funcLimpezaDAO.close();

		for (FuncionarioLimpeza funcionarioLimpeza : funcionariosLimpeza) {
			System.out.println(funcionarioLimpeza);
		}
		System.out.println("===========================================");
	}

	public static FuncionarioLimpeza findByNome() {
		FuncionarioLimpeza funcLimpeza = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();

		try {
			funcLimpeza = (FuncionarioLimpeza) em
					.createQuery("SELECT fl FROM FuncionarioLimpeza fl WHERE fl.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
		}

		return funcLimpeza;
	}

}
