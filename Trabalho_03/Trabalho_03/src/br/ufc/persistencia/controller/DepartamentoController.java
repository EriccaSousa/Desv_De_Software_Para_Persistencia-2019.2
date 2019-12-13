package br.ufc.persistencia.controller;

import java.util.Scanner;

import br.ufc.persistencia.CRUD.DepartamentoCRUD;
import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.dao.mongodb.DepartamentoDBDAO;
import br.ufc.persistencia.dao.mongodb.GenericMongoDBDAO;
import br.ufc.persistencia.model.Departamento;

public class DepartamentoController {

	static GenericDAO<Departamento> departamentoGenericDAO = new GenericMongoDBDAO<Departamento>("departamentos");
	static FindDAO<Departamento> departamentoFindDAO = new DepartamentoDBDAO();

	static Scanner read = new Scanner(System.in);

	public static void cadastroDepartamento() {
		try {
			System.out.println("\n --- CADASTRO DEPARTAMENTO --- \n");
			System.out.print("Nome: ");
			String nome = read.nextLine();

			DepartamentoCRUD.cadastrar(departamentoGenericDAO, nome);
		} catch (Exception e) {
			System.out.println("\nErro!\n");
		}
	}

	public static void findAllDepartamento() {
		System.out.println("\n --- LISTAR DEPARTAMENTOS --- \n");
		DepartamentoCRUD.findAll(departamentoFindDAO);
	}

	public static Departamento findByIdDepartamento() {
		System.out.print("Id: ");
		String id = read.nextLine();

		return DepartamentoCRUD.findById(departamentoFindDAO, id);
	}

	public static void updateNomeDepartamento() {
		System.out.println("\n --- ATUALIZAR NOME DEPARTAMENTO --- \n");
		Departamento departamento = findByIdDepartamento();

		System.out.print("Novo nome: ");
		String newNome = read.nextLine();

		DepartamentoCRUD.updateNome(departamentoGenericDAO, departamentoFindDAO, departamento, newNome);

	}

	public static void deleteDepartamento() {
		System.out.println("\n --- DELETAR DEPARTAMENTO POR ID --- \n");
		System.out.print("Id: ");
		String id = read.nextLine();

		DepartamentoCRUD.deleteById(departamentoGenericDAO, id);
	}
}
