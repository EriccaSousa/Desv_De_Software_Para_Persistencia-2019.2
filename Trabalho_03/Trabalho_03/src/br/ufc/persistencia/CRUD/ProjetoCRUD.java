package br.ufc.persistencia.CRUD;

import java.util.List;

import org.bson.types.ObjectId;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.model.Projeto;

public class ProjetoCRUD {
	/* --- Funcionando --- */
	public static void cadastrar(GenericDAO<Projeto> projetoDao, Projeto projeto) {

		 projetoDao.insert(projeto);
	}

	/* --- Funcionando --- */
	public static void findAll(FindDAO<Projeto> projetoFindDAO) {
		List<Projeto> projetos = projetoFindDAO.findAll();

		for (Projeto projeto : projetos) {
			System.out.println(projeto);
		}
	}

	/* --- Funcionando --- */
	public static Projeto findById(FindDAO<Projeto> projetoFindDAO, String id) {
		Projeto projeto = projetoFindDAO.findById(new ObjectId(id));

		return projeto;
	}

	/* --- Não Funciona --- */
	public static List<Projeto> findByNome(FindDAO<Projeto> projetoFindDAO, String nome) {
		List<Projeto> projeto = projetoFindDAO.findByNome(nome);

		return projeto;
	}

	/* --- Funcionando --- */
	public static void updateNome(GenericDAO<Projeto> projetoDAO, FindDAO<Projeto> projetoFindDAO,
			Projeto projeto) {

		projetoDAO.update(projeto);

		System.out.println(projeto);
	}

	/* --- Funciona --- */
	public static void deleteById(GenericDAO<Projeto> projetoDAO, String id) {
		projetoDAO.delete(new ObjectId(id));
	}
}
