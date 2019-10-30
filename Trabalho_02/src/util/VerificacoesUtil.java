package util;

import controller.DepartamentoCRUD;
import controller.ProjetoCRUD;
import model.Departamento;
import model.Projeto;

public class VerificacoesUtil {

	public static boolean verificaExistenciaDepartamento(String nome) {
		Departamento departamento = DepartamentoCRUD.findByNome(nome);

		if (departamento == null)
			return true;
		else
			return false;

	}

	public static boolean verificaExistenciaProjetos(String nome) {
		Projeto projeto = ProjetoCRUD.findByNome(nome);

		if (projeto == null)
			return true;
		else
			return false;
	}

}
