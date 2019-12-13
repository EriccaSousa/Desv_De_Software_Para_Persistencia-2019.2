package br.ufc.persistencia.view;

import br.ufc.persistencia.controller.DepartamentoController;
import br.ufc.persistencia.util.TextosUtil;

public class DepartamentoMenus {

	// Adicionar um menu para Update;

	public static void menuIniciaDepartamento() {
		do {
			TextosUtil.menuInicialDepartamentos();
			int opcao = TextosUtil.lerOpcao();

			switch (opcao) {
			case 1:
				DepartamentoController.cadastroDepartamento(); // Funcionando
				break;
			case 2:
				DepartamentoController.findAllDepartamento(); // Funcionando
				break;
			case 3:
				System.out.println(
						"\n --- BUSCAR DEPARTAMENTO POR ID ---\n" + DepartamentoController.findByIdDepartamento()); // Funcionando
				break;
			case 4:
				DepartamentoController.updateNomeDepartamento(); // Funcionando
				break;
			case 5:
				DepartamentoController.deleteDepartamento(); // Funcionando
			case 6:
				MenuInicial.menuInicial();
				break;
			default:
				TextosUtil.opcaoInvalida();
				break;
			}
		} while (true);
	}

}
