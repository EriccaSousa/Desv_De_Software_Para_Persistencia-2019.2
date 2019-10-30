package view;

import controller.DepartamentoCRUD;
import util.TextosUtil;

public class MenusDepartamento {

	static int opcao;

	public static void menuInicialByDepartamento() {
		do {
			System.out.println("\n\t--- Departamentos ---");

			opcao = TextosUtil.menuCRUDs();

			switch (opcao) {
			case 1:
				DepartamentoCRUD.criarDepartamento();
				break;
			case 2:
				menuBuscarByDepartamento();
				break;
			case 3:
				DepartamentoCRUD.deleteByNome();
				break;
			case 4:
				MenusIniciais.menuFuncionarios();
				break;
			default:
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

	public static void menuBuscarByDepartamento() {
		do {
			System.out.println("\n\t--- Buscar Departamentos ---");

			opcao = TextosUtil.menuBuscar();

			switch (opcao) {
			case 1:
				DepartamentoCRUD.findAll();
				break;
			case 2:
				System.out.println("\n" + DepartamentoCRUD.findByNome() + "\n");
				break;
			case 3:
				menuInicialByDepartamento();
				break;
			default:
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

}
