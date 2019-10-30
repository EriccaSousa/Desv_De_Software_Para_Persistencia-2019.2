package view;

import controller.FuncionarioCRUD;
import controller.PesquisadorCRUD;
import util.TextosUtil;

public class MenusPesquisador {

	static int opcao;

	public static void menuInicialByPesquisador() {
		do {
			System.out.println("\n\t--- Pesquisadores---");

			opcao = TextosUtil.menuCRUDs();

			switch (opcao) {
			case 1:
				System.out.println("Aqui!");
				FuncionarioCRUD.criarPesquisador(3);
				break;
			case 2:
				menuBuscarByPesquisador();
				break;
			case 3:
				PesquisadorCRUD.deleteByNome();
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

	public static void menuBuscarByPesquisador() {
		do {
			System.out.println("\n\t--- Buscar Pesquisadores ---");

			opcao = TextosUtil.menuBuscar();

			switch (opcao) {
			case 1:
				PesquisadorCRUD.findAll();
				break;
			case 2:
				System.out.println("\n" + PesquisadorCRUD.findByNome() + "\n");
				break;
			case 3:
				menuInicialByPesquisador();
				break;
			default:
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}
}
