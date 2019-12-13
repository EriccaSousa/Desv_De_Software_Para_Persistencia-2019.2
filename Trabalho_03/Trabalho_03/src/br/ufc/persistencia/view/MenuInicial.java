package br.ufc.persistencia.view;

import br.ufc.persistencia.util.TextosUtil;

public class MenuInicial {

	public static void menuInicial() {
		do {
			TextosUtil.menuInicial();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				DepartamentoMenus.menuIniciaDepartamento(); // Feito
				break;
			case 2:
				FuncionarioMenus.menuFuncionarios();
				break;
			case 3:
				// Projetos
				break;
			case 4:
				// Dependentes
				break;
			case 5:
				System.exit(0);
				break;
			default:
				TextosUtil.opcaoInvalida();
				break;
			}

		} while (true);
	}
}
