package view;

import controller.FuncLimpezaCRUD;
import controller.FuncionarioCRUD;
import util.TextosUtil;

public class MenusFuncLimpeza {

	static int opcao;

	public static void menuInicialByFuncLimpeza() {
		do {
			System.out.println("\n\t--- Funcionários de Limpeza ---");

			opcao = TextosUtil.menuCRUDs();

			switch (opcao) {
			case 1:
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
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

	public static void menuBuscarByFuncLimpeza() {
		do {
			System.out.println("\n\t--- Buscar Funcionários de Limpeza:");

			opcao = TextosUtil.menuBuscar();

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
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

}
