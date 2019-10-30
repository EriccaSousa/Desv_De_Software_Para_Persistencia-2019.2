package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dao.FuncionarioLimpezaDAO;
import dao.FuncionarioLimpezaJPA_DAO;
import dao.PesquisadorDAO;
import dao.PesquisadorJPA_DAO;
import model.FuncionarioLimpeza;
import model.Pesquisador;

public class FuncLimpezaCRUD {
	static Scanner read = new Scanner(System.in);

	public static void findAll() {
		FuncionarioLimpezaDAO funcLimpezaDAO = new FuncionarioLimpezaJPA_DAO();

		List<FuncionarioLimpeza> funcionariosLimpeza = funcLimpezaDAO.findAll();
		funcLimpezaDAO.close();
		if (funcionariosLimpeza == null) {
			System.out.println("Não existe nenhum funcionario de limpeza cadastrado no banco");
			return;
		}
		for (FuncionarioLimpeza funcionarioLimpeza : funcionariosLimpeza) {
			System.out.println(funcionarioLimpeza);
		}
		System.out.println("\n");
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
			if (funcLimpeza == null)
				System.out.println("Funcionario de limpeza não foi encontrado ou não existe no banco!");
			e.printStackTrace();
		}

		return funcLimpeza;
	}

	public static void deleteByNome() {
		FuncionarioLimpezaDAO funDao = new FuncionarioLimpezaJPA_DAO();
		FuncionarioLimpezaDAO funDao2 = new FuncionarioLimpezaJPA_DAO();

		try {
			funDao.beginTransaction();

			FuncionarioLimpeza funLimpeza = findByNome();
			funLimpeza.setDepartamento(null);

			funDao.delete(funLimpeza);

			funDao.close();

			funDao2.beginTransaction();

			System.out.println("Confirme o nome do Funcionario de limpeza a ser removido.");
			FuncionarioLimpeza funLimpeza2 = findByNome();

			funLimpeza2.setDepartamento(null);

			funDao2.delete(funLimpeza);

			funDao2.close();
			System.out.println("Funcionario de limpeza deletado com sucesso!");
			funDao.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("Erro!");
			funDao.rollback();
			e.printStackTrace();
		} finally {
			funDao.close();
		}

		System.out.println("\n");
	}

}
