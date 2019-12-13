package br.ufc.persistencia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.ufc.persistencia.CRUD.FunLimpezaCRUD;
import br.ufc.persistencia.CRUD.SecretarioCRUD;
import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.dao.mongodb.FunLimpezaDBDAO;
import br.ufc.persistencia.dao.mongodb.GenericMongoDBDAO;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.Dependente;
import br.ufc.persistencia.model.FuncionarioLimpeza;
import br.ufc.persistencia.model.Secretario;
import br.ufc.persistencia.util.DataUtil;

public class FuncionarioDeLimpezaController {
	static GenericDAO<FuncionarioLimpeza> funLimpezaGenericDAO = new GenericMongoDBDAO<FuncionarioLimpeza>(
			"funcionariosLimpeza");
	static FindDAO<FuncionarioLimpeza> funcLimpezaFindDao = new FunLimpezaDBDAO();

	static Scanner read = new Scanner(System.in);

	public static void cadastraFunLimpeza() {
		try {
			System.out.println("\n --- CADASTRO FUNCIONÁRIO DE LIMPEZA(A) --- \n");
			System.out.print("Nome: ");
			String nome = read.nextLine();

			System.out.print("Endereço: ");
			String endereco = read.nextLine();

			System.out.print("Sexo: ");
			String sexo = read.nextLine();

			System.out.print("Data Aniversário: ");
			Date dataAniver = DataUtil.formatData(read.nextLine());

			System.out.print("Grau Escolaridade: ");
			String grauEscolaridade = read.nextLine();

			System.out.print("Salário: ");
			double salario = read.nextDouble();
			read.nextLine();

			System.out.print("Departamento: ");
			Departamento departamento = DepartamentoController.findByIdDepartamento();

			System.out.print("Cargo: ");
			String cargo = read.nextLine();

			System.out.print("Jornada de trabalho: ");
			String jornadaTrab = read.nextLine();

			System.out.print("Responsavel: ");
			FuncionarioLimpeza funcionarioLimpezaResp = FuncionarioDeLimpezaController.findByIdFunLimpeza();

			System.out.println("Deseja adicionar Dependentes agora?\n1 - Sim   2 - Não");
			int opcao = read.nextInt();
			read.nextLine();

			if (opcao == 1) {
				List<Dependente> dependentes = new ArrayList<Dependente>();
				System.out.println("Digite a Quantidade de dependentes que deseja cadastrar");
				int opt = read.nextInt();
				read.nextLine();
				for(int i = 0; i < opt; i++) {
					dependentes.add(DependenteController.retornardp());
					
				}

				FuncionarioLimpeza funcionarioLimpeza = new FuncionarioLimpeza(nome, endereco, sexo, dataAniver,
						salario, dependentes, departamento, cargo, jornadaTrab, funcionarioLimpezaResp);
			} else {
				FuncionarioLimpeza funcionarioLimpeza = new FuncionarioLimpeza(nome, endereco, sexo, dataAniver,
						salario, null, departamento, cargo, jornadaTrab, funcionarioLimpezaResp);

				funLimpezaGenericDAO.insert(funcionarioLimpeza);
			}
		} catch (Exception e) {
			System.out.println("\nErro!\n");
			e.printStackTrace();
		}
	}

	public static void findAllFuncionarioLimpeza() {
		System.out.println("\n --- LISTAR FUNCIONÁRIOS DE LIMPEZA --- \n");
		FunLimpezaCRUD.findAll(funcLimpezaFindDao);
	}

	public static FuncionarioLimpeza findByIdFunLimpeza() {
		System.out.println("\n --- BUSCAR FUNCIONÁRIOS DE LIMPEZA POR ID --- \n");
		System.out.print("Id: ");
		String id = read.nextLine();

		return FunLimpezaCRUD.findById(funcLimpezaFindDao, id);
	}

	public static void updateNomeFunLimpeza() {
		System.out.println("\n --- ATUALIZAR NOME FUNCIONÁRIO DE LIMPEZA --- \n");
		FuncionarioLimpeza funcionarioLimpeza = findByIdFunLimpeza();

		System.out.print("Novo nome: ");
		String newNome = read.nextLine();

		FunLimpezaCRUD.updateNome(funLimpezaGenericDAO, funcLimpezaFindDao, funcionarioLimpeza, newNome);
	}

	public static void updateEnderecoFunLimpeza() {
		System.out.println("\n --- ATUALIZAR ENDEREÇO FUNCIONÁRIO DE LIMPEZA --- \n");
		FuncionarioLimpeza funcionarioLimpeza = findByIdFunLimpeza();

		System.out.print("Novo endereço: ");
		String newEndereco = read.nextLine();

		FunLimpezaCRUD.updateEndereco(funLimpezaGenericDAO, funcLimpezaFindDao, funcionarioLimpeza, newEndereco);
	}

	public static void updateSalarioFunLimpeza() {
		System.out.println("\n --- ATUALIZAR SALÁRIO FUNCIONÁRIO DE LIMPEZA --- \n");
		FuncionarioLimpeza funcionarioLimpeza = findByIdFunLimpeza();

		System.out.print("Novo salário: ");
		double newSalario = read.nextDouble();

		FunLimpezaCRUD.updateSalario(funLimpezaGenericDAO, funcLimpezaFindDao, funcionarioLimpeza, newSalario);
	}

	public static void updateDepartamentoFunLimpeza() {
		System.out.println("\n --- ATUALIZAR DEPARTAMENTO FUNCIONÁRIO DE LIMPEZA --- \n");
		FuncionarioLimpeza funcionarioLimpeza = findByIdFunLimpeza();

		System.out.print("Novo Departamento: ");
		Departamento newDepartamento = DepartamentoController.findByIdDepartamento();

		FunLimpezaCRUD.updateDepartamento(funLimpezaGenericDAO, funcLimpezaFindDao, funcionarioLimpeza,
				newDepartamento);
	}

	public static void deleteFunLimpeza() {
		System.out.println("\n --- DELETAR FUNCIONÁRIO DE LIMPEZA --- \n");

		System.out.print("Id: ");
		String id = read.nextLine();

		FunLimpezaCRUD.deleteById(funLimpezaGenericDAO, id);
	}
}
