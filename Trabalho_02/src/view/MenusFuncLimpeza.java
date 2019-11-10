package view;

import java.util.Scanner;

import controller.FuncLimpezaCRUD;
import controller.FuncionarioCRUD;

public class MenusFuncLimpeza {
	static Scanner read = new Scanner(System.in);
	static int opcao;

	public static void menuInicialByFuncLimpeza() {
		do {
			System.out.println("Menu Funcionário Limpeza:");
			System.out.println("[ 1 ] Cadastra Funcionário Limpeza\n[ 2 ] Buscar Funcionário Limpeza \n[ 3 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Aqui!");
				FuncionarioCRUD.criarFuncLmpeza(1);
				break;
			case 2:
				menuBuscarByFuncLimpeza();
				break;
			case 3:
				FuncLimpezaCRUD.deleteByNome();
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

	public static void menuBuscarByFuncLimpeza() {
		do {
			System.out.println("Menu Pesquisar Funcionário Limpeza:");
			System.out.println(
					"[ 1 ] Listar todos os Funcionário Limpeza\n[ 2 ] Buscar Funcionário Limpeza por nome\n[ 3 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				FuncLimpezaCRUD.findAll();
				break;
			case 2:
				System.out.println("\n" + FuncLimpezaCRUD.findByNome() + "\n");
				break;
			case 3:
				menuInicialByFuncLimpeza();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}

}
