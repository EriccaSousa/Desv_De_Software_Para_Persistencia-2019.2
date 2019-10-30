package view;

import controller.DependenteCRUD;
import util.TextosUtil;

public class MenusDependente {

	static int opcao;

	public static void menuInicialByDependente() {
		do {
			System.out.println("\n\t--- Dependentes ---");

			opcao = TextosUtil.menuCRUDs();

			switch (opcao) {
			case 1:
				DependenteCRUD.criarDependente();
				break;
			case 2:
				menuBuscarByDependente();
				break;
			case 3:
				DependenteCRUD.deleteByNome();
				break;
			case 4:
				MenusIniciais.menuInicial();
				break;
			default:
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

	public static void menuBuscarByDependente() {
		do {
			System.out.println("\n\t--- Buscar Dependentes ---");

			opcao = TextosUtil.menuBuscar();

			switch (opcao) {
			case 1:
				DependenteCRUD.findAll();
				break;
			case 2:
				System.out.println("\n" + DependenteCRUD.findByNome() + "\n");
				break;
			case 3:
				menuInicialByDependente();
				break;
			default:
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

}
