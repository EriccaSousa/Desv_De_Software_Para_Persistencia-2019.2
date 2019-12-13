package br.ufc.persistencia.view;

import br.ufc.persistencia.controller.SecretarioController;
import br.ufc.persistencia.util.TextosUtil;

public class SecretarioMenus {

	public static void menuInicialSecretario() {
		do {
			TextosUtil.menuInicialFuncionarios();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				SecretarioController.cadastrarSecretario();
				break;
			case 2:
				SecretarioController.findAllSecretarios();
				break;
			case 3:
				System.out.println(
						"\n --- BUSCAR SECRETÁRIO(A) POR ID --- \n" + SecretarioController.findByIdSecretario());
				break;
			case 4:
				menuUpdateSecretario();
				break;
			case 5:
				SecretarioController.deleteSecretario();
				break;
			case 6:
				FuncionarioMenus.menuFuncionarios();
			default:
				TextosUtil.opcaoInvalida();
				break;
			}
		} while (true);
	}

	public static void menuUpdateSecretario() {
		do {
			TextosUtil.menuUpdate();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				SecretarioController.updateNomeSecretario();
				break;
			case 2:
				SecretarioController.updateEnderecoSecretario();
				break;
			case 3:
				SecretarioController.updateSalarioSecretario();
				break;
			case 4:
				SecretarioController.updateDepartamentoSecretario();
				break;
			case 5:
				SecretarioMenus.menuInicialSecretario();
				break;
			default:
				TextosUtil.opcaoInvalida();
				break;
			}
		} while (true);
	}

}
