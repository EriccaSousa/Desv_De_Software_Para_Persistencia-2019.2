package controller;

import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.FuncionarioLimpezaDAO;
import dao.FuncionarioLimpezaJPA_DAO;
import dao.PesquisadorDAO;
import dao.PesquisadorJPA_DAO;
import dao.SecretarioDAO;
import dao.SecretarioJPA_DAO;
import model.Departamento;
import model.Funcionario;
import model.FuncionarioLimpeza;
import model.Pesquisador;
import model.Projeto;
import model.Secretario;

public class FuncionarioCRUD {
	static Scanner read = new Scanner(System.in);

	public static Funcionario retornarFuncionario(int opcao) {

		System.out.println("Informe os dados do Funcionario(a)\n");
		System.out.println("Nome: ");
		String nome = read.nextLine();
		System.out.println("Endereco: ");
		String endereco = read.nextLine();
		System.out.println("Sexo: ");
		String sexo = read.nextLine();
		System.out.println("Data Aniversario: ");
		String dataAniver = read.nextLine();
		System.out.println("Salario: ");
		double salario = read.nextDouble();
		read.nextLine();

		Departamento departamento = DepartamentoCRUD.findByNome();

		/*System.out.println(
				"Informe a funcao do seu FuncionÃ¡rio:\n[ 1 ] Funcionario de Limpeza\n[ 2 ] Secretario(a)\n[ 3 ] Pesquisador(a)");
		int opcao = read.nextInt();
		read.nextLine();*/

		if (opcao == 1) {
			System.out.println("Cargo:");
			String cargo = read.nextLine();
			System.out.println("Jornada de trabalho: ");
			String jornadaTrab = read.nextLine();
			
			System.out.println("Funcionario possui gerente? 1 - sim, 0 - não");
			int op = read.nextInt();
			FuncionarioLimpeza funcionarioLimpeza;
			if(op == 1)
				funcionarioLimpeza = new FuncionarioLimpeza(nome, endereco, sexo, dataAniver, salario,
					null, departamento, cargo, jornadaTrab, FuncLimpezaCRUD.findByNome());
			else
				funcionarioLimpeza = new FuncionarioLimpeza(nome, endereco, sexo, dataAniver, salario,
						null, departamento, cargo, jornadaTrab, null);
			return funcionarioLimpeza;

		} else if (opcao == 2) {
			System.out.println("Grau de escolaridade: ");
			String grauEscolar = read.nextLine();

			Funcionario secretario = new Secretario(nome, endereco, sexo, dataAniver, salario, null, departamento,
					grauEscolar);

			return secretario;
		} else if (opcao == 3) {
			System.out.println("Ã�rea atuaÃ§Ã£o: ");
			String areaAtuacao = read.nextLine();

			System.out.println("InformaÃ§Ãµes do projeto em que irÃ¡ trabalhara:");
			Projeto projeto = ProjetoCRUD.findByNome();

			Funcionario pesquisador = new Pesquisador(nome, endereco, sexo, dataAniver, salario, null, departamento,
					areaAtuacao, projeto);
			return pesquisador;
		} else {
			System.out.println("OpÃ§Ã£o invÃ¡lida");

			return null;
		}

	}

	public static void criarSecretario(int opcao) {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();
		try {
			secretarioDAO.beginTransaction();
			secretarioDAO.save(retornarFuncionario(opcao));
			secretarioDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Pesquisador!\n");

			secretarioDAO.rollback();
			e.printStackTrace();
		} finally {
			secretarioDAO.close();
		}
	}

	public static void criarPesquisador(int opcao) {
		PesquisadorDAO pesquisadorDAO = new PesquisadorJPA_DAO();

		try {
			pesquisadorDAO.beginTransaction();
			pesquisadorDAO.save(retornarFuncionario(opcao));
			pesquisadorDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Pesquisador!\n");

			pesquisadorDAO.rollback();
			e.printStackTrace();
		} finally {
			pesquisadorDAO.close();
		}
	}

	public static void criarFuncLmpeza(int opcao) {
		FuncionarioLimpezaDAO funcLimpezaDAO = new FuncionarioLimpezaJPA_DAO();

		try {
			funcLimpezaDAO.beginTransaction();
			funcLimpezaDAO.save(retornarFuncionario(opcao));
			funcLimpezaDAO.commit();
		} catch (IllegalStateException | PersistenceException e) {
			System.out.println("\nErro ao salvar Funcionario de Limpeza!\n");

			funcLimpezaDAO.rollback();
			e.printStackTrace();
		} finally {
			funcLimpezaDAO.close();
		}
	}

}
