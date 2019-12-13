package br.ufc.persistencia.model;

import java.util.Date;
import java.util.List;

public abstract class Funcionario extends Bean {

	private String nome;
	private String endereco;
	private String sexo;
	private Date dataAniver;
	private double salario;
	private List<Dependente> dependentes;
	private Departamento departamento;

	public Funcionario() {
		super();

	}

	public Funcionario(String nome, String endereco, String sexo, Date dataAniver, double salario,
			List<Dependente> dependentes, Departamento departamento) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.sexo = sexo;
		this.dataAniver = dataAniver;
		this.salario = salario;
		this.dependentes = dependentes;
		this.departamento = departamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataAniver() {
		return dataAniver;
	}

	public void setDataAniver(Date dataAniver) {
		this.dataAniver = dataAniver;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Nome: " + this.nome + "\nEndereco: " + this.endereco + "\nSexo: " + this.sexo + "\nData Aniversário: "
				+ this.dataAniver + "Salario: " + this.salario + "\nDependentes: " + this.dependentes
				+ "\nDepartamento: " + this.departamento + "\n";
	}

	public void addDependente(Dependente dependente) {
		this.dependentes.add(dependente);
	}

}
