package br.ufc.persistencia.util;

import java.util.Scanner;

public class TextosUtil {

	public static Scanner read = new java.util.Scanner(System.in);

	public static int lerOpcao() {
		System.out.print("\nOpção: ");
		int opcao = read.nextInt();
		read.nextLine();

		return opcao;
	}

	public static void opcaoInvalida() {
		System.out.println("\nOpção inválida!\n");
	}

	public static void menuInicial() {
		System.out.println("1 - Departamentos\n2 - Funcionarios\n3 - Projetos\n4 - Dependentes\n5 - Sair\n");
	}

	public static void menuInicialDepartamentos() {
		System.out.println(
				"1 - Cadastrar Departamento\n2 - Listar Departamentos\n3 - Buscar\n4 - Atualizar informações\n5 - Deletar\n6 - Voltar");
	}

	public static void menuFuncionarios() {
		System.out.println("1 - Secretário(a)\n2 - Pesquisador(a)\n3 - Funcionario(a) Limpeza\n4 - Voltar\n");
	}

	public static void menuInicialFuncionarios() {
		System.out.println(
				"1 - Cadastrar\n2 - Listar todos\n3 - Buscar\n4 - Atualizar informações\n5 - Deletar\n6 - Voltar");
	}

	public static void menuUpdate() {
		System.out.println(
				"1 - Atualizar nome\n2 - Atualizar endereço\n3 - Atualizar salário\n4 - Atualizar Departamento\n5 - Voltar");
	}

}
