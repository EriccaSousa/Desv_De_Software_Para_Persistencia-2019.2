package controller;

import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.ProjetoDAO;
import dao.ProjetoJPA_DAO;
import model.Departamento;
import model.Projeto;

public class ProjetoCRUD {
	static Scanner read = new Scanner(System.in);

	public static void criarProjeto() {
		ProjetoDAO projetoDAO = new ProjetoJPA_DAO();

		try {
			projetoDAO.beginTransaction();

			System.out.println("Informe os dados para Projeto\nNúmero de identificação: ");
			String id = read.nextLine();
			System.out.println("Nome: ");
			String nome = read.nextLine();
			System.out.println("Período: ");
			String periodo = read.nextLine();

			Departamento departamento = DepartamentoCRUD.findById();

			projetoDAO.save(new Projeto(id, nome, periodo, departamento));
			projetoDAO.commit();

		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Departamento!\n");

			projetoDAO.rollback();
			e.printStackTrace();
		} finally {
			projetoDAO.close();
		}

	}

}
