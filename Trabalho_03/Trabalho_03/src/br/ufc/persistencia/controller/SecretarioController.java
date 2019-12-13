package br.ufc.persistencia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.ufc.persistencia.CRUD.SecretarioCRUD;
import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.dao.mongodb.GenericMongoDBDAO;
import br.ufc.persistencia.dao.mongodb.SecretarioDBDAO;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.Dependente;
import br.ufc.persistencia.model.Secretario;
import br.ufc.persistencia.util.DataUtil;

public class SecretarioController {

	static GenericDAO<Secretario> secretarioGenericDAO = new GenericMongoDBDAO<Secretario>("secretarios");
	static FindDAO<Secretario> secretarioFindDAO = new SecretarioDBDAO();

	static Scanner read = new Scanner(System.in);
	/*
	 * String nome, String endereco, String sexo, Date dataAniver, double salario,
	 * List<Dependente> dependentes, Departamento departamento, String
	 * grauEscolaridade
	 */

	public static void cadastrarSecretario() {
		try {
			System.out.println("\n --- CADASTRO SECRETÃ�RIO(A) --- \n");
			System.out.print("Nome: ");
			String nome = read.nextLine();

			System.out.print("EndereÃ§o: ");
			String endereco = read.nextLine();

			System.out.print("Sexo: ");
			String sexo = read.nextLine();

			System.out.print("Data AniversÃ¡rio: ");
			Date dataAniver = DataUtil.formatData(read.nextLine());

			System.out.print("Grau Escolaridade: ");
			String grauEscolaridade = read.nextLine();

			System.out.print("SalÃ¡rio: ");
			double salario = read.nextDouble();
			read.nextLine();

			System.out.print("Departamento: ");
			Departamento departamento = DepartamentoController.findByIdDepartamento();

			System.out.println("Deseja adicionar Dependentes agora?\n1 - Sim   2 - NÃ£o");
			int opcao = read.nextInt();
			read.nextLine();

			if (opcao == 1) {
				List<Dependente> dependentes = new ArrayList<Dependente>();
				System.out.println("Digite a Quantidade de dependentes que deseja cadastrar");
				int opt = read.nextInt();
				read.nextLine();
				for (int i = 0; i < opt; i++) {
					dependentes.add(DependenteController.retornardp());

				}
				Secretario secretario = new Secretario(nome, endereco, sexo, dataAniver, salario, dependentes,
						departamento, grauEscolaridade);
				SecretarioCRUD.cadastrar(secretarioGenericDAO, secretario);

			} else {
				Secretario secretario = new Secretario(nome, endereco, sexo, dataAniver, salario, null, departamento,
						grauEscolaridade);
				SecretarioCRUD.cadastrar(secretarioGenericDAO, secretario);


			}


		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
		}
	}

	public static void findAllSecretarios() {
		System.out.println("\n --- LISTAR SECRETÃ�RIOS --- \n");
		SecretarioCRUD.findAll(secretarioFindDAO);
	}

	public static Secretario findByIdSecretario() {
		System.out.println("\n --- BUSCAR SECRETÃ�RIO POR ID --- \n");
		System.out.print("Id: ");
		String id = read.nextLine();

		return SecretarioCRUD.findById(secretarioFindDAO, id);

	}

	public static void updateNomeSecretario() {
		System.out.println("\n --- ATUALIZAR NOME SECRETÃ�RIO(A) --- \n");
		Secretario secretario = findByIdSecretario();

		System.out.print("Novo nome: ");
		String newNome = read.nextLine();

		SecretarioCRUD.updateNome(secretarioGenericDAO, secretarioFindDAO, secretario, newNome);
	}

	public static void updateEnderecoSecretario() {
		System.out.println("\n --- ATUALIZAR ENDEREÃ‡O SECRETÃ�RIO(A) --- \n");
		Secretario secretario = findByIdSecretario();

		System.out.print("Novo endereÃ§o: ");
		String newEndereco = read.nextLine();

		SecretarioCRUD.updateEndereco(secretarioGenericDAO, secretarioFindDAO, secretario, newEndereco);
	}

	public static void updateSalarioSecretario() {
		System.out.println("\n --- ATUALIZAR SALÃ�RIO SECRETÃ�RIO(A) --- \n");
		Secretario secretario = findByIdSecretario();

		System.out.print("Novo salÃ¡rio: ");
		double newSalario = read.nextDouble();

		SecretarioCRUD.updateSalario(secretarioGenericDAO, secretarioFindDAO, secretario, newSalario);
	}

	public static void updateDepartamentoSecretario() {
		System.out.println("\n --- ATUALIZAR DEPARTAMENTO SECRETÃ�RIO(A) --- \n");
		Secretario secretario = findByIdSecretario();

		System.out.print("Novo Departamento: ");
		Departamento newDepartamento = DepartamentoController.findByIdDepartamento();

		SecretarioCRUD.updateDepartamento(secretarioGenericDAO, secretarioFindDAO, secretario, newDepartamento);
	}

	public static void deleteSecretario() {
		System.out.println("\n --- DELETAR SECRETÃ�RIO(A) --- \n");

		System.out.print("Id: ");
		String id = read.nextLine();

		SecretarioCRUD.deleteById(secretarioGenericDAO, id);
	}

}
