package br.ufc.persistencia.view;

import br.ufc.persistencia.controller.FuncionarioDeLimpezaController;
import br.ufc.persistencia.util.TextosUtil;

public class FuncLimpezaMenus {

	public static void menuInicialFuncLimpeza() {
		do {
			TextosUtil.menuInicialFuncionarios();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				FuncionarioDeLimpezaController.cadastraFunLimpeza();
				break;
			case 2:
				FuncionarioDeLimpezaController.findAllFuncionarioLimpeza();
				break;
			case 3:
				System.out.println("\n --- BUSCAR FUNCIONÁRIO LIMPEZA POR ID --- \n"
						+ FuncionarioDeLimpezaController.findByIdFunLimpeza());
				break;
			case 4:
				menuUpdateFuncLimpeza();
				break;
			case 5:
				FuncionarioDeLimpezaController.deleteFunLimpeza();
				break;
			case 6:
				FuncionarioMenus.menuFuncionarios();
			default:
				TextosUtil.opcaoInvalida();
				break;
			}
		} while (true);
	}

	public static void menuUpdateFuncLimpeza() {
		do {
			TextosUtil.menuUpdate();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				FuncionarioDeLimpezaController.updateNomeFunLimpeza();
				break;
			case 2:
				FuncionarioDeLimpezaController.updateEnderecoFunLimpeza();
				break;
			case 3:
				FuncionarioDeLimpezaController.updateSalarioFunLimpeza();
				break;
			case 4:
				FuncionarioDeLimpezaController.updateDepartamentoFunLimpeza();
				break;
			case 5:
				FuncLimpezaMenus.menuInicialFuncLimpeza();
				break;
			default:
				TextosUtil.opcaoInvalida();
				break;
			}
		} while (true);
	}
}
