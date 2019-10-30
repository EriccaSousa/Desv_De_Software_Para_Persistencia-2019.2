package util;

import java.util.Scanner;

public class TextosUtil {

	static Scanner read = new Scanner(System.in);
	static int opcao;

	public static int textoMenuInicial() {
		System.out.println("\n\t--- Menu Inicial ---");
		System.out.println("\n[ 1 ] Departamentos\n[ 2 ] Funcionários\n[ 3 ] Projetos\n[ 4 ] Dependentes\n[ 5 ] Sair");
		System.out.print("\nEscolha uma opção: ");
		opcao = read.nextInt();

		return opcao;
	}

	public static int textoMenuFuncionarios() {
		System.out.println("\n\t--- Funcionários ---");
		System.out.println("\n[ 1 ] Pesquisadores\n[ 2 ] Funcionarios de Limpeza\n[ 3 ] Secretários\n[ 4 ] Voltar");
		System.out.print("\nEscolha uma opção: ");
		opcao = read.nextInt();

		return opcao;
	}

	public static void textoDefault() {
		System.out.println("\nOpção inválida!\n");
	}

	public static int menuCRUDs() {
		System.out.println("\n[ 1 ] Cadastrar\n[ 2 ] Buscar\n[ 3 ] Deletar\n[ 4 ] Voltar ");
		System.out.print("\nEscolha uma opção: ");
		opcao = read.nextInt();

		return opcao;
	}

	public static int menuBuscar() {
		System.out.println("\n[ 1 ] Listar todos\n[ 2 ] Buscar por nome\n[ 3 ] Voltar");
		System.out.print("\nEscolha uma opção: ");
		opcao = read.nextInt();

		return opcao;
	}

	public static void demarcacao() {
		System.out.println("\n==================================================\n");
	}

	public static void nomeNaoEncontrado() {
		System.out.println("\nO nome informado não está cadastrado no sistema.\n");
	}

	public static void nomeJaCadastrado() {
		System.out.println("\nEste nome já está cadastrado no sistema.\nPor favor, escolha outro nome.");
	}

	public static void deletadoComSucesso() {
		System.out.println("\nRemoção realizada com sucesso!\n");
	}

}
