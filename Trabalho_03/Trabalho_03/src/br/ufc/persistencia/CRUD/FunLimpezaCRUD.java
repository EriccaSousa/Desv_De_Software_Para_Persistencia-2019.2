package br.ufc.persistencia.CRUD;

import java.util.List;

import org.bson.types.ObjectId;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.FuncionarioLimpeza;

public class FunLimpezaCRUD {
	/* --- Aparentemente Funcionando --- */
	public static void cadastrar(GenericDAO<FuncionarioLimpeza> funLimpezaDao, FuncionarioLimpeza funlimpeza) {

		funLimpezaDao.insert(funlimpeza);
		System.out.println(funlimpeza);
	}

	/* --- Funcionando --- */
	public static void findAll(FindDAO<FuncionarioLimpeza> funLimpezaDao) {
		List<FuncionarioLimpeza> funlimpezas = funLimpezaDao.findAll();

		for (FuncionarioLimpeza funlimpeza : funlimpezas) {
			System.out.println(funlimpeza);
		}
	}

	/* --- Funcionando --- */
	public static FuncionarioLimpeza findById(FindDAO<FuncionarioLimpeza> funLimpezaDao, String id) {
		FuncionarioLimpeza funlimpeza = funLimpezaDao.findById(new ObjectId(id));

		return funlimpeza;
	}

	/* --- Não testei --- */
	public static void updateNome(GenericDAO<FuncionarioLimpeza> funLimpezaDao,
			FindDAO<FuncionarioLimpeza> funLimpezaFindDao, FuncionarioLimpeza funcionarioLimpeza, String newNome) {
		funcionarioLimpeza.setNome(newNome);

		funLimpezaDao.update(funcionarioLimpeza);

		System.out.println(funcionarioLimpeza);
	}

	/* --- Não testei --- */
	public static void updateEndereco(GenericDAO<FuncionarioLimpeza> funLimpezaDao,
			FindDAO<FuncionarioLimpeza> funLimpezaFindDao, FuncionarioLimpeza funcionarioLimpeza, String newEndereco) {
		funcionarioLimpeza.setEndereco(newEndereco);

		funLimpezaDao.update(funcionarioLimpeza);

		System.out.println(funcionarioLimpeza);
	}

	/* --- Não testei --- */
	public static void updateSalario(GenericDAO<FuncionarioLimpeza> funLimpezaDao,
			FindDAO<FuncionarioLimpeza> funLimpezaFindDao, FuncionarioLimpeza funcionarioLimpeza, double newSalario) {
		funcionarioLimpeza.setSalario(newSalario);

		funLimpezaDao.update(funcionarioLimpeza);

		System.out.println(funcionarioLimpeza);
	}

	/* --- Não testei --- */
	public static void updateDepartamento(GenericDAO<FuncionarioLimpeza> funLimpezaDao,
			FindDAO<FuncionarioLimpeza> funLimpezaFindDao, FuncionarioLimpeza funcionarioLimpeza,
			Departamento newDepartamento) {
		funcionarioLimpeza.setDepartamento(newDepartamento);

		funLimpezaDao.update(funcionarioLimpeza);

		System.out.println(funcionarioLimpeza);
	}

	/* --- Funcionando --- */
	public static void deleteById(GenericDAO<FuncionarioLimpeza> funLimpezaDao, String id) {
		funLimpezaDao.delete(new ObjectId(id));
	}
}
