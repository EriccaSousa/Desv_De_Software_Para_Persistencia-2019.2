package view;

import java.util.Scanner;

import controller.DepartamentoCRUD;

public class MenusDepartamento {
	static Scanner read = new Scanner(System.in);
	static int opcao;

	public static void menuInicialByDepartamento() {
		do {
			System.out.println("Menu Departamento:");
			System.out.println(
					"[ 1 ] Cadastrar Departamentos\n[ 2 ] Buscar Departamentos\n[ 3 ] Deletar Departamentos\n[ 4 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				DepartamentoCRUD.criarDepartamento();
				break;
			case 2:
				menuBuscarByDepartamento();
				break;
			case 3:
				menuDeletarByDepartamento();
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

	public static void menuBuscarByDepartamento() {
		do {
			System.out.println("Menu Pesquisar Departamento:");
			System.out.println(
					"[ 1 ] Listar todos os Departamentos\n[ 2 ] Buscar Departamentos por nome\n[ 3 ] Buscar Departamentos por número de identificação\n[ 4 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				DepartamentoCRUD.findAll();
				break;
			case 2:
				System.out.println("\n" + DepartamentoCRUD.findByNome() + "\n");
				break;
			case 3:
				// System.out.println("\n" + DepartamentoCRUD.findById() + "\n");
				break;
			case 4:
				menuInicialByDepartamento();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}

	public static void menuDeletarByDepartamento() {
		do {
			System.out.println("Menu Deletar Departamento:");
			System.out.println(
					"[ 1 ] Deletar Departamentos por nome\n[ 2 ] Deletar Departamentos por número de identificação\n[ 3 ] Voltar ");
			System.out.print("Escolha uma opção: ");
			opcao = read.nextInt();

			switch (opcao) {
			case 1:
				DepartamentoCRUD.deleteByNome();
				break;
			case 2:
				// DepartamentoCRUD.deleteById();
				break;
			case 3:
				menuInicialByDepartamento();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (true);
	}
}
