package br.ufc.persistencia.controller;

import java.util.Scanner;

import br.ufc.persistencia.CRUD.DepartamentoCRUD;
import br.ufc.persistencia.CRUD.DependenteCRUD;
import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.dao.mongodb.DepartamentoDBDAO;
import br.ufc.persistencia.dao.mongodb.DependenteDBDAO;
import br.ufc.persistencia.dao.mongodb.GenericMongoDBDAO;
import br.ufc.persistencia.model.Dependente;

public class DependenteController {
	static GenericDAO<Dependente> dependenteGenericDAO = new GenericMongoDBDAO<Dependente>("dependente");
	static FindDAO<Dependente> dependenteFindDAO = new DependenteDBDAO();

	static Scanner read = new Scanner(System.in);

	static Dependente dependente = new Dependente();
	
	public static Dependente retornardp() {
		cadastroDependente();
		return dependente;
	}

	public static void cadastroDependente() {
		try {
			System.out.println("\n --- CADASTRO DEPENDENTES --- \n");
			System.out.print("Nome: ");
			String nome = read.nextLine();
			System.out.println("Sexo: ");
			String sexo = read.nextLine();
			System.out.println("grauParentesco: ");
			String grauParentesco = read.nextLine();
			
			
			
			dependente.setNome(nome);
			dependente.setSexo(sexo);
			dependente.setGrauParentesco(grauParentesco);
			dependente.setFuncionario(null);

		} catch (Exception e) {
			System.out.println("\nErro!\n");
		}
	}

	public static void findAllDependente() {
		System.out.println("\n --- LISTAR DEPENDENTES --- \n");
		DependenteCRUD.findAll(dependenteFindDAO);
	}

	public static Dependente findByIdDependente() {
		System.out.print("Id: ");
		String id = read.nextLine();

		return DependenteCRUD.findById(dependenteFindDAO, id);
	}

	public static void updateNomeDependente() {
		System.out.println("\n --- ATUALIZAR NOME DEPENDENTE --- \n");
		Dependente dependente = findByIdDependente();
		System.out.print("Novo nome: ");
		String newNome = read.nextLine();
		dependente.setNome(newNome);

		DependenteCRUD.updateNome(dependenteGenericDAO, dependenteFindDAO, dependente);

	}

	public static void deleteDepartamento() {
		System.out.println("\n --- DELETAR DEPARTAMENTO POR ID --- \n");
		System.out.print("Id: ");
		String id = read.nextLine();

		DependenteCRUD.deleteById(dependenteGenericDAO, id);
	}
}
