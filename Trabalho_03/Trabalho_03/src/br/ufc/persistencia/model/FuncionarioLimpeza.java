package br.ufc.persistencia.model;

import java.util.Date;
import java.util.List;

import org.bson.Document;

public class FuncionarioLimpeza extends Funcionario {

	private String cargo;
	private String jornadaTrab;
	private FuncionarioLimpeza responsavel;

	public FuncionarioLimpeza() {

	}

	public FuncionarioLimpeza(String nome, String endereco, String sexo, Date dataAniver, double salario,
			List<Dependente> dependentes, Departamento departamento, String cargo, String jornadaTrab,
			FuncionarioLimpeza responsavel) {
		super(nome, endereco, sexo, dataAniver, salario, dependentes, departamento);
		this.cargo = cargo;
		this.jornadaTrab = jornadaTrab;
		this.responsavel = responsavel;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getJornadaTrab() {
		return jornadaTrab;
	}

	public void setJornadaTrab(String jornadaTrab) {
		this.jornadaTrab = jornadaTrab;
	}

	public FuncionarioLimpeza getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(FuncionarioLimpeza responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public String toString() {
		return "Funcionário de Limpeza" + "\nId: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereço: "
				+ this.getEndereco() + "\nSexo: " + this.getSexo() + "\nData Aniversário: " + this.getDataAniver()
				+ "\nSalário: " + this.getSalario() + "\nCargo: " + this.cargo + "\nDepartamento: "
				+ this.getDepartamento() + "\nJornada de Trabalho: " + this.jornadaTrab + "\nDependentes: "
				+ this.getDependentes() + "\n";
	}

	@Override
	public String toJson() {
		String json = "{\"id\" : \"" + id + "\", \"nome\" : \"" + getNome() + "\", \"endereco\" : \"" + getEndereco()
				+ "\", \"sexo\" : \"" + getSexo() + "\", \"dataAniver\" : \"" + getDataAniver() + "\", \"salario\" : \""
				+ getSalario() + "\", \"departamento\" : \"" + getDepartamento() + "\", \"cargo\" : \""
				+ this.getCargo() + "\", \"jornadaTrab\" : \"" + this.getJornadaTrab() + "\", \"responsavel\" : \""
				+ this.getResponsavel() + "\"}";

		return json;
	}

	public static FuncionarioLimpeza fromDocument(Document doc) {
		if (doc == null) {
			return null;
		}
		FuncionarioLimpeza funlimpeza = new FuncionarioLimpeza();

		funlimpeza.id = doc.getObjectId("_id");
		funlimpeza.setNome(doc.getString("nome"));
		funlimpeza.setEndereco(doc.getString("endereco"));
		funlimpeza.setSexo(doc.getString("sexo"));
		// secretario.setDataAniver(doc.getDate("dataAniver"));
		// secretario.setSalario(doc.getDouble("salario"));
		funlimpeza.cargo = doc.getString("cargo");
		funlimpeza.jornadaTrab = doc.getString("jornadaTrab");
		// funlimpeza.setDepartamento(doc.get("departamento", Departamento.class));
		// funlimpeza.setResponsavel(doc.get("responsavel", FuncionarioLimpeza.class));
		// secretario.setDependentes(doc.getList("dependentes", Dependente.class));

		return funlimpeza;
	}

}
