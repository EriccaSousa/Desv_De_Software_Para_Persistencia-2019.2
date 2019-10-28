package view;

import java.util.Scanner;

import controller.SecretarioCRUD;

public class MenuSecretario {

	static Scanner read = new Scanner(System.in);
	static int opcao;

	public static void menuInicialBySecretario() {
		do {
			System.out.println("Menu Secretário:");
			System.out.println(
					"[ 1 ] Cadastrar Secretários\n[ 2 ] Buscar Secretários \n[ 3 ] Deletar Secretários \n[ 4 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				SecretarioCRUD.criarSecretario();
				break;
			case 2:
				menuBuscarBySecretario();
				break;
			case 3:
				menuDeletarBySecretario();
				break;
			case 4:
				MenusIniciais.menuFuncionarios();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}

	public static void menuBuscarBySecretario() {
		do {
			System.out.println("Menu Pesquisar Secretário:");
			System.out.println(
					"[ 1 ] Listar todos os Secretários\n[ 2 ] Buscar Secretários por nome\n[ 3 ] Buscar Secretários por número de identificação\n[ 4 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				SecretarioCRUD.findAll();
				break;
			case 2:
				System.out.println(SecretarioCRUD.findByNome());
				break;
			case 3:
				System.out.println(SecretarioCRUD.findById());
				break;
			case 4:
				menuInicialBySecretario();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}

	public static void menuDeletarBySecretario() {
		do {
			System.out.println("Menu Deletar Secretário:");
			System.out.println(
					"[ 1 ] Deletar Secretários por nome\n[ 2 ] Deletar Secretários por número de identificação\n[ 3 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				SecretarioCRUD.deleteByNome();
				break;
			case 2:
				SecretarioCRUD.deleteById();
				break;
			case 3:
				menuInicialBySecretario();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}
}
