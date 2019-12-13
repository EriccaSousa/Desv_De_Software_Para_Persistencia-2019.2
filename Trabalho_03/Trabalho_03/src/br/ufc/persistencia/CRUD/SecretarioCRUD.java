package br.ufc.persistencia.CRUD;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.Dependente;
import br.ufc.persistencia.model.Secretario;

public class SecretarioCRUD {

	/* --- Aparentemente Funcionando --- */
	public static void cadastrar(GenericDAO<Secretario> secretarioDao, Secretario secretario) {

		secretarioDao.insert(secretario);
		System.out.println(secretario);
	}

	/* --- Funcionando --- */
	public static void findAll(FindDAO<Secretario> secretarioDao) {
		List<Secretario> secretarios = secretarioDao.findAll();

		for (Secretario secretario : secretarios) {
			System.out.println(secretario);
		}
	}

	/* --- Funcionando --- */
	public static Secretario findById(FindDAO<Secretario> secretarioDao, String id) {
		Secretario secretarios = secretarioDao.findById(new ObjectId(id));

		return secretarios;
	}

	/* --- Não testei --- */
	public static void updateNome(GenericDAO<Secretario> secretarioDao, FindDAO<Secretario> secretarioFindDao,
			Secretario secretario, String newNome) {

		secretario.setNome(newNome);

		secretarioDao.update(secretario);

		System.out.println(secretario);
	}

	/* --- Não testei --- */
	public static void updateEndereco(GenericDAO<Secretario> secretarioDao, FindDAO<Secretario> secretarioFindDao,
			Secretario secretario, String newEndereco) {

		secretario.setEndereco(newEndereco);

		secretarioDao.update(secretario);

		System.out.println(secretario);
	}

	/* --- Não testei --- */
	public static void updateSalario(GenericDAO<Secretario> secretarioDao, FindDAO<Secretario> secretarioFindDao,
			Secretario secretario, double newSalario) {

		secretario.setSalario(newSalario);

		secretarioDao.update(secretario);

		System.out.println(secretario);
	}

	/* --- Não testei --- */
	public static void updateDepartamento(GenericDAO<Secretario> secretarioDao, FindDAO<Secretario> secretarioFindDao,
			Secretario secretario, Departamento newDepartamento) {

		secretario.setDepartamento(newDepartamento);

		secretarioDao.update(secretario);

		System.out.println(secretario);
	}

	/* --- Funcionando --- */
	public static void deleteById(GenericDAO<Secretario> secretarioDao, String id) {
		secretarioDao.delete(new ObjectId(id));
	}

}
