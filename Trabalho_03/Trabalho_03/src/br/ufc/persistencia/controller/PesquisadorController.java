package br.ufc.persistencia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.ufc.persistencia.CRUD.DependenteCRUD;
import br.ufc.persistencia.CRUD.PesquisadorCRUD;
import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.dao.mongodb.DependenteDBDAO;
import br.ufc.persistencia.dao.mongodb.GenericMongoDBDAO;
import br.ufc.persistencia.dao.mongodb.PesquisadorDBDAO;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.Dependente;
import br.ufc.persistencia.model.Pesquisador;
import br.ufc.persistencia.model.Projeto;
import br.ufc.persistencia.util.DataUtil;

public class PesquisadorController {

	static GenericDAO<Pesquisador> pesquisadorGenericDAO = new GenericMongoDBDAO<Pesquisador>("pesquisadores");
	static FindDAO<Pesquisador> pesquisadorFindDAO = new PesquisadorDBDAO();
	static GenericDAO<Dependente> dependenteGenericDAO = new GenericMongoDBDAO<Dependente>("dependentes");
	static FindDAO<Dependente> dependenteFindDAO = new DependenteDBDAO();

	static Scanner read = new Scanner(System.in);

	public static void cadastrarPesquisador() {
		try {
			System.out.println("\n --- CADASTRO PESQUISADOR --- \n");

			System.out.print("Nome: ");
			String nome = read.nextLine();

			System.out.print("EndereÃ§o: ");
			String endereco = read.nextLine();

			System.out.print("Sexo: ");
			String sexo = read.nextLine();

			System.out.print("dataAniver: ");
			Date dataAniver = DataUtil.formatData(read.nextLine());

			System.out.print("Ã�rea de atuaÃ§Ã£o: ");
			String areaAtuacao = read.nextLine();

			System.out.print("SalÃ¡rio: ");
			double salario = read.nextDouble();
			read.nextLine();

			System.out.print("Departamento");
			Departamento departamento = DepartamentoController.findByIdDepartamento();

			System.out.println("Deseja adicionar Dependentes agora?\n1 - Sim   2 - NÃ£o");
			int opcao = read.nextInt();
			read.nextLine();

			System.out.print("Projeto: ");
			List<Projeto> projetos = null;

			if (opcao == 1) {
				List<Dependente> dependentes = new ArrayList<Dependente>();
				System.out.println("Digite a Quantidade de dependentes que deseja cadastrar");
				int opt = read.nextInt();
				read.nextLine();
				for(int i = 0; i < opt; i++) {
					dependentes.add(DependenteController.retornardp());
					
				}
				PesquisadorCRUD.cadastrar(pesquisadorGenericDAO, nome, endereco, sexo, dataAniver, dependentes, areaAtuacao,
						salario, departamento);
			} else {
				PesquisadorCRUD.cadastrar(pesquisadorGenericDAO, nome, endereco, sexo, dataAniver, null, areaAtuacao,
						salario, departamento);
			}
			System.out.println("Em quantos projetos você trabalha?");
			System.out.println("Digite a quantidade de horas trabalhadas");
		} catch (Exception e) {
			System.out.println("\nErro!\n");
			e.printStackTrace();
		}
	}

	public static void findAllPesquisador() {
		System.out.println("\n --- LISTAR TODOS OS PESQUISADORES --- \n");
		PesquisadorCRUD.findAll(pesquisadorFindDAO);
	}

	public static Pesquisador findByIdPesquisador() {
		System.out.print("Id: ");
		String id = read.nextLine();

		return PesquisadorCRUD.findById(pesquisadorFindDAO, id);
	}

	public static void deletePesquisador() {
		System.out.println("\n --- DELETEAR PESQUISADOR --- \n");
		System.out.println("Id: ");
		String id = read.nextLine();

		PesquisadorCRUD.deleteById(pesquisadorGenericDAO, id);
	}

	public static void updateNomePesquisador() {
		System.out.println("\n --- ATUALIZAR NOME PESQUISADOR(A) --- \n");
		Pesquisador pesquisador = findByIdPesquisador();

		System.out.print("Novo nome: ");
		String newNome = read.nextLine();

		PesquisadorCRUD.updateNome(pesquisadorGenericDAO, pesquisador, newNome);
	}

	public static void updateEnderecoPesquisador() {
		System.out.println("\n --- ATUALIZAR ENDEREÃ‡O PESQUISADOR(A) --- \n");
		Pesquisador pesquisador = findByIdPesquisador();

		System.out.print("Novo endereÃ§o: ");
		String newEndereco = read.nextLine();

		PesquisadorCRUD.updateEndereco(pesquisadorGenericDAO, pesquisador, newEndereco);
	}

	public static void updateSalarioPesquisador() {
		System.out.println("\n --- ATUALIZAR SALÃ�RIO PESQUISADOR(A) --- \n");
		Pesquisador pesquisador = findByIdPesquisador();

		System.out.print("Novo salÃ¡rio: ");
		double newSalario = read.nextDouble();

		PesquisadorCRUD.updateSalario(pesquisadorGenericDAO, pesquisador, newSalario);
	}

	public static void updateDepartamentoPesquisador() {
		System.out.println("\n --- ATUALIZAR DEPARTAMENTO PESQUISADOR(A) --- \n");
		Pesquisador pesquisador = findByIdPesquisador();

		System.out.print("Novo departamento: ");
		Departamento newDepartamento = DepartamentoController.findByIdDepartamento();

		PesquisadorCRUD.updateDepartamento(pesquisadorGenericDAO, pesquisador, newDepartamento);
	}

	public static void deletepesquisador() {
		System.out.println("\n --- DELETAR PESQUISADOR --- \n");

		System.out.print("Id: ");
		String id = read.nextLine();

		PesquisadorCRUD.deleteById(pesquisadorGenericDAO, id);
	}
}
