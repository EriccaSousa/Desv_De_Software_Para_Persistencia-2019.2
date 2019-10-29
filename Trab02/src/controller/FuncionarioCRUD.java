package controller;

import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.*;
import model.*;

public class FuncionarioCRUD {
	static Scanner read = new Scanner(System.in);

	private static Funcionario funcionario;

	public static Funcionario retornarFuncionario() {

		System.out.println("Informe os dados do Funcionario(a)----------\nNumero de identificacao: ");
		String id = read.nextLine();
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
		Departamento departamento = DepartamentoCRUD.findById();

		System.out.println(
				"Informe a funcao do seu agregado:\n[ 1 ] Funcionario de Limpeza\n[ 2 ] Secretario(a)\n[ 3 ] Pesquisador(a)");
		int opcao = read.nextInt();

		System.out.println("Deseja Cadastrar algum dependente? 1 - sim");
		int op = read.nextInt();
		if (op == 1) {
			System.out.println("Quantos dependentes deseja cadastrar? máximo 3");
			int qtd = read.nextInt();
			if (qtd <= 3) {
				int i = 0;
				while (i < qtd) {
					DependenteCRUD.criarDependente(opcao);
				}
			} else
				System.out.println("o Máximo é 3");

		}

		if (opcao == 1) {
			return null;
		} else if (opcao == 2) {
			System.out.println("Grau de Escolaridade: ");
			String grauEscolar = read.nextLine();
			return new Secretario(nome, endereco, sexo, dataAniver, salario, grauEscolar, departamento);
		} else if (opcao == 3) {
			System.out.println("Area de Atuação");
			String areaAtuacao = read.nextLine();
			return new Pesquisador(nome, endereco, sexo, dataAniver, salario, departamento, areaAtuacao, null);
		} else {
			System.out.println("opção invalida!");
		}

		return null;
	}
	
	public static void criarFunLimpeza() {
		
	}
	public static void criarSecretario() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();
		try {
			secretarioDAO.beginTransaction();
			secretarioDAO.save(retornarFuncionario());
			secretarioDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Dependente!\n");

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
			System.out.println("\nErro ao salvar Dependente!\n");

			pesquisadorDAO.rollback();
			e.printStackTrace();
		} finally {
			pesquisadorDAO.close();
		}

	}
}
