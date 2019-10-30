package view;

import java.util.Scanner;

import controller.ProjetoCRUD;

public class MenusProjeto {
	static Scanner read = new Scanner(System.in);
	static int opcao;

	public static void menuInicialByProjeto() {
		do {
			System.out.println("Menu Projeto:");
			System.out.println("[ 1 ] Cadastrar Projeto\n[ 2 ] Buscar Projeto\n[ 3 ] Deletar Projeto\n[ 4 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				ProjetoCRUD.criarProjeto();
				break;
			case 2:
				menuBuscarByProjeto();
				break;
			case 3:
				menuDeletarByDProjeto();
				break;
			case 4:
				MenusIniciais.menuInicial();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}

	public static void menuBuscarByProjeto() {
		do {
			System.out.println("Menu Pesquisar Projeto:");
			System.out.println(
					"[ 1 ] Listar todos os Projetos\n[ 2 ] Buscar Projetos por nome\n[ 3 ] Buscar Projetos por número de identificação\n[ 4 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				ProjetoCRUD.findAll();
				break;
			case 2:
				System.out.println("\n" + ProjetoCRUD.findByNome() + "\n");
				break;
			case 3:
				// System.out.println("\n" + ProjetoCRUD.findById() + "\n");
				break;
			case 4:
				menuInicialByProjeto();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}

	public static void menuDeletarByDProjeto() {
		do {
			System.out.println("Menu Deletar Projeto:");
			System.out.println(
					"[ 1 ] Deletar Projeto por nome\n[ 2 ] Deletar Projeto por número de identificação\n[ 3 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				ProjetoCRUD.deleteByNome();
				break;
			case 2:
				// ProjetoCRUD.deleteById();
				break;
			case 3:
				menuInicialByProjeto();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}
}
