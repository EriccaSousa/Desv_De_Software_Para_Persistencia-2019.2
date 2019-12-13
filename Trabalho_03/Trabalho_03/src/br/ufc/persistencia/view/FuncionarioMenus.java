package br.ufc.persistencia.view;

import br.ufc.persistencia.util.TextosUtil;

public class FuncionarioMenus {

	public static void menuFuncionarios() {
		do {
			TextosUtil.menuFuncionarios();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				SecretarioMenus.menuInicialSecretario();
				break;
			case 2:
				PesquisadorMenus.menuInicialPesquisador();
				break;
			case 3:
				FuncLimpezaMenus.menuInicialFuncLimpeza();
				break;
			case 4:
				MenuInicial.menuInicial();
			default:
				TextosUtil.opcaoInvalida();
				break;
			}
		} while (true);

	}

}
