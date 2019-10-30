package view;

import util.TextosUtil;

public class MenusIniciais {

	static int opcao;

	public static void menuInicial() {
		do {

			opcao = TextosUtil.textoMenuInicial();

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
				System.out.println("\nVolte sempre!\n");
				System.exit(0);
				break;
			default:
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

	public static void menuFuncionarios() {
		do {

			opcao = TextosUtil.textoMenuFuncionarios();

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
				TextosUtil.textoDefault();
				break;
			}

		} while (true);
	}

}
