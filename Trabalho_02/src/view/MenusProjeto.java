package view;

import controller.ProjetoCRUD;
import util.TextosUtil;

public class MenusProjeto {

	static int opcao;

	public static void menuInicialByProjeto() {
		do {
			System.out.println("\n\t--- Menu Projetos ---");

			opcao = TextosUtil.menuCRUDs();

			switch (opcao) {
			case 1:
				ProjetoCRUD.criarProjeto();
				break;
			case 2:
				menuBuscarByProjeto();
				break;
			case 3:
				ProjetoCRUD.deleteByNome();
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

	public static void menuBuscarByProjeto() {
		do {
			System.out.println("\n\t--- Buscar Projetos ---");

			opcao = TextosUtil.menuBuscar();

			switch (opcao) {
			case 1:
				ProjetoCRUD.findAll();
				break;
			case 2:
				System.out.println("\n" + ProjetoCRUD.findByNome() + "\n");
				break;
			case 3:
				menuInicialByProjeto();
				break;
			default:
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}
}
