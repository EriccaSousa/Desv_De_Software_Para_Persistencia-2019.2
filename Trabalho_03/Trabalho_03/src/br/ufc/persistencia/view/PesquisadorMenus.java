package br.ufc.persistencia.view;

import br.ufc.persistencia.controller.PesquisadorController;
import br.ufc.persistencia.util.TextosUtil;

public class PesquisadorMenus {

	public static void menuInicialPesquisador() {
		do {
			TextosUtil.menuInicialFuncionarios();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				PesquisadorController.cadastrarPesquisador();
				break;
			case 2:
				PesquisadorController.findAllPesquisador();
				break;
			case 3:
				System.out.println(
						"\n --- BUSCAR PESQUISADOR POR ID --- \n" + PesquisadorController.findByIdPesquisador());
				break;
			case 4:
				menuUpdatePesquisador();
				break;
			case 5:
				PesquisadorController.deletePesquisador();
				break;
			case 6:
				FuncionarioMenus.menuFuncionarios();
			default:
				TextosUtil.opcaoInvalida();
				break;
			}
		} while (true);
	}

	public static void menuUpdatePesquisador() {
		do {
			TextosUtil.menuUpdate();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				PesquisadorController.updateNomePesquisador();
				break;
			case 2:
				PesquisadorController.updateEnderecoPesquisador();
				break;
			case 3:
				PesquisadorController.updateSalarioPesquisador();
				break;
			case 4:
				PesquisadorController.updateDepartamentoPesquisador();
				break;
			case 5:
				PesquisadorMenus.menuInicialPesquisador();
				break;
			default:
				TextosUtil.opcaoInvalida();
				break;
			}
		} while (true);
	}

}
