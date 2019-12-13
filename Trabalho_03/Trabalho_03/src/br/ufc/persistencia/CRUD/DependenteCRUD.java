package br.ufc.persistencia.CRUD;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.Dependente;
import br.ufc.persistencia.model.Funcionario;
import br.ufc.persistencia.model.Secretario;

public class DependenteCRUD {

	/* --- Aparentemente Funcionando --- */
	public static void cadastrar(GenericDAO<Dependente> dependenteDao, Dependente dependente) {
		// Falta a lógica pra buscar o funcionario
		dependenteDao.insert(dependente);
		System.out.println(dependente);
	}

	/* --- Funcionando --- */
	public static void findAll(FindDAO<Dependente> dependenteFindDao) {
		List<Dependente> dependentes = dependenteFindDao.findAll();

		for (Dependente dependente : dependentes) {
			System.out.println(dependente);
		}
	}

	/* --- Funcionando --- */
	public static Dependente findById(FindDAO<Dependente> dependenteFindDao, String id) {
		Dependente dependentes = dependenteFindDao.findById(new ObjectId(id));

		return dependentes;
	}

	/* --- Não testei --- */
	public static void updateNome(GenericDAO<Dependente> dependenteDao, FindDAO<Dependente> dependenteFindDao, Dependente dependentes) {

		dependenteDao.update(dependentes);

		System.out.println(dependentes);
	}

	/* --- Funcionando --- */
	public static void deleteById(GenericDAO<Dependente> dependenteDao, String id) {
		dependenteDao.delete(new ObjectId(id));
	}

}
