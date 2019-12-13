package br.ufc.persistencia.CRUD;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.dao.mongodb.PesquisadorDBDAO;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.Dependente;
import br.ufc.persistencia.model.Pesquisador;
import br.ufc.persistencia.model.Projeto;
import br.ufc.persistencia.model.Secretario;

public class PesquisadorCRUD {

	/* --- Aparentemente Funcionando --- */
	public static void cadastrar(GenericDAO<Pesquisador> pesquisadorDao, String nome, String endereco, String sexo,
			Date dataAniver, List<Dependente> dependentes, String areaAtuacao,  Double salario,
			Departamento departamento) {

		Pesquisador pesquisador = new Pesquisador();

		pesquisador.setNome(nome);
		pesquisador.setEndereco(endereco);
		pesquisador.setSexo(sexo);
		pesquisador.setDataAniver(dataAniver);
		pesquisador.setSalario(salario);
		pesquisador.setDependentes(dependentes);
		pesquisador.setDepartamento(departamento);
		pesquisador.setAreaAtuacao(areaAtuacao);

		pesquisadorDao.insert(pesquisador);
		System.out.println(pesquisador);
	}


	/* --- Funcionando --- */
	public static void findAll(FindDAO<Pesquisador> pesquisadorDao) {
		List<Pesquisador> pesquisadores = pesquisadorDao.findAll();

		for (Pesquisador pesquisador : pesquisadores) {
			System.out.println(pesquisador);
		}
	}

	/* --- Funcionando --- */
	public static Pesquisador findById(FindDAO<Pesquisador> pesquisadorDao, String id) {
		Pesquisador pesquisador = pesquisadorDao.findById(new ObjectId(id));

		return pesquisador;
	}

	/* --- Não testei --- */
	public static void updateNome(GenericDAO<Pesquisador> pesquisadorDao, Pesquisador pesquisador, String newNome) {
		pesquisador.setNome(newNome);

		pesquisadorDao.update(pesquisador);

		System.out.println(pesquisador);
	}

	/* --- Não testei --- */
	public static void updateEndereco(GenericDAO<Pesquisador> pesquisadorDao, Pesquisador pesquisador,
			String newEndereco) {
		pesquisador.setEndereco(newEndereco);

		pesquisadorDao.update(pesquisador);

		System.out.println(pesquisador);
	}

	/* --- Não testei --- */
	public static void updateSalario(GenericDAO<Pesquisador> pesquisadorDao, Pesquisador pesquisador,
			double newSalario) {
		pesquisador.setSalario(newSalario);

		pesquisadorDao.update(pesquisador);

		System.out.println(pesquisador);
	}

	/* --- Não testei --- */
	public static void updateDepartamento(GenericDAO<Pesquisador> pesquisadorDao, Pesquisador pesquisador,
			Departamento newDepartamento) {
		pesquisador.setDepartamento(newDepartamento);

		pesquisadorDao.update(pesquisador);

		System.out.println(pesquisador);
	}

	/* --- Funcionando --- */
	public static void deleteById(GenericDAO<Pesquisador> pesquisadorDao, String id) {
		pesquisadorDao.delete(new ObjectId(id));
	}
}
