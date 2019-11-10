package view;

import java.util.Scanner;

public class MenusIniciais {

	static Scanner read = new Scanner(System.in);
	static int opcao;

	public static void menuInicial() {
		do {
			System.out.println("Menu Inicial:");
			System.out
					.println("[ 1 ] Departamentos\n[ 2 ] Funcionarios\n[ 3 ] Projetos\n[ 4 ] Dependentes\n[ 5 ] Sair");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				MenusDepartamento.menuInicialByDepartamento();
				break;
			case 2:
				menuFuncionarios();
				break;
			case 3:
				MenusProjeto.menuInicialByProjeto();
				break;
			case 4:
				MenusDependente.menuInicialByDependente();
				break;
			case 5:
				System.out.println("FIM");
				System.exit(0);
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}

	public static void menuFuncionarios() {
		do {
			System.out.println("Menu Funcionarios:");
			System.out.println("[ 1 ] Pesquisadores\n[ 2 ] Funcionarios de Limpeza\n[ 3 ] Secretário\n[ 4 ] Voltar");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				MenusPesquisador.menuInicialByPesquisador();
				break;
			case 2:
				MenusFuncLimpeza.menuInicialByFuncLimpeza();
				break;
			case 3:
				MenuSecretario.menuInicialBySecretario();
				break;
			case 4:
				menuInicial();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}

}
