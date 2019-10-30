package view;

import controller.FuncionarioCRUD;
import controller.SecretarioCRUD;
import util.TextosUtil;

public class MenuSecretario {

	static int opcao;

	public static void menuInicialBySecretario() {
		do {
			System.out.println("\n\t--- Secretários ---");

			opcao = TextosUtil.menuCRUDs();

			switch (opcao) {
			case 1:
				FuncionarioCRUD.criarSecretario(2);
				break;
			case 2:
				menuBuscarBySecretario();
				break;
			case 3:
				SecretarioCRUD.deleteByNome();
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

	public static void menuBuscarBySecretario() {
		do {
			System.out.println("\n\t--- Buscar Secretários ---");

			opcao = TextosUtil.menuBuscar();

			switch (opcao) {
			case 1:
				SecretarioCRUD.findAll();
				break;
			case 2:
				System.out.println("\n" + SecretarioCRUD.findByNome() + "\n");
				break;
			case 3:
				menuInicialBySecretario();
				break;
			default:
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

}
