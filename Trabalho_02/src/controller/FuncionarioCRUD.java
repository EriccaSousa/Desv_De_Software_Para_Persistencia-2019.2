package controller;

import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.PesquisadorDAO;
import dao.PesquisadorJPA_DAO;
import dao.SecretarioDAO;
import dao.SecretarioJPA_DAO;
import model.Departamento;
import model.Funcionario;
import model.FuncionarioLimpeza;
import model.Pesquisador;
import model.Secretario;

public class FuncionarioCRUD {
	static Scanner read = new Scanner(System.in);

	public static Funcionario retornarFuncionario() {

		System.out.println("Informe os dados do Funcionario(a)\n");
		System.out.println("Nome: ");
		String nome = read.nextLine();
		System.out.println("Endereco: ");
		String endereco = read.nextLine();
		System.out.println("Sexo: ");
		String sexo = read.nextLine();
		System.out.println("Data Aniversario: ");
		String dataAniver = read.nextLine();
		System.out.println("Salario: ");
		double salario = read.nextDouble();
		read.nextLine();

		// Departamento departamento = DepartamentoCRUD.findById();

		System.out.println(
				"Informe a funcao do seu Funcionário:\n[ 1 ] Funcionario de Limpeza\n[ 2 ] Secretario(a)\n[ 3 ] Pesquisador(a)");
		int opcao = read.nextInt();
		read.nextLine();

		if (opcao == 1) {
			System.out.println("Cargo:");
			String cargo = read.nextLine();
			System.out.println("Jornada de trabalho: ");
			String jornadaTrab = read.nextLine();

			FuncionarioLimpeza funcionarioLimpeza = new FuncionarioLimpeza();

			return funcionarioLimpeza;

		} else if (opcao == 2) {
			System.out.println("Grau de escolaridade: ");
			String grauEscolar = read.nextLine();

			Funcionario secretario = new Secretario(nome, endereco, sexo, dataAniver, salario, grauEscolar);

			return secretario;
		} else if (opcao == 3) {
			System.out.println("Área atuação: ");
			String areaAtuacao = read.nextLine();

			Funcionario pesquisador = new Pesquisador(nome, endereco, sexo, dataAniver, salario, areaAtuacao);
			return pesquisador;
		} else {
			System.out.println("Opção inválida");

			return null;
		}

	}

	public static void criarSecretario() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();
		try {
			secretarioDAO.beginTransaction();
			secretarioDAO.save(retornarFuncionario());
			secretarioDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Pesquisador!\n");

			secretarioDAO.rollback();
			e.printStackTrace();
		} finally {
			secretarioDAO.close();
		}
	}

	public static void criarPesquisador() {
		PesquisadorDAO pesquisadorDAO = new PesquisadorJPA_DAO();

		try {
			pesquisadorDAO.beginTransaction();
			pesquisadorDAO.save(retornarFuncionario());
			pesquisadorDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Pesquisador!\n");

			pesquisadorDAO.rollback();
			e.printStackTrace();
		} finally {
			pesquisadorDAO.close();
		}
	}

}
