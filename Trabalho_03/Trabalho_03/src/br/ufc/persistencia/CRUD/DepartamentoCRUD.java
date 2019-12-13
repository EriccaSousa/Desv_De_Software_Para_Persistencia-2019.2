package br.ufc.persistencia.CRUD;

import java.util.List;

import org.bson.types.ObjectId;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.model.Departamento;

public class DepartamentoCRUD {

	/* --- Funcionando --- */
	public static void cadastrar(GenericDAO<Departamento> departamentoDao, String nome) {
		Departamento departamento = new Departamento();

		departamento.setNome(nome);

		departamentoDao.insert(departamento);
	}

	/* --- Funcionando --- */
	public static void findAll(FindDAO<Departamento> departFindDAO) {
		List<Departamento> departamentos = departFindDAO.findAll();

		for (Departamento departamento : departamentos) {
			System.out.println(departamento);
		}
	}

	/* --- Funcionando --- */
	public static Departamento findById(FindDAO<Departamento> departFindDAO, String id) {
		Departamento departamento = departFindDAO.findById(new ObjectId(id));

		return departamento;
	}

	/* --- Não Funciona --- */
	public static List<Departamento> findByNome(FindDAO<Departamento> departFindDAO, String nome) {
		List<Departamento> departamentos = departFindDAO.findByNome(nome);

		return departamentos;
	}

	/* --- Funcionando --- */
	public static void updateNome(GenericDAO<Departamento> departamentoDao, FindDAO<Departamento> departFindDAO,
			Departamento departamento, String newNome) {

		departamento.setNome(newNome);

		departamentoDao.update(departamento);

		System.out.println(departamento);
	}

	/* --- Funciona --- */
	public static void deleteById(GenericDAO<Departamento> departamentoDao, String id) {
		departamentoDao.delete(new ObjectId(id));
	}

}
