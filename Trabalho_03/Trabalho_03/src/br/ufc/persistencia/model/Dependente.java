package br.ufc.persistencia.model;

import org.bson.Document;

public class Dependente extends Bean {

	private String nome;
	private String sexo;
	private String grauParentesco;
	private Funcionario funcionario;

	public Dependente() {

	}

	public Dependente(String nome, String sexo, String grauParentesco, Funcionario funcionario) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.grauParentesco = grauParentesco;
		this.funcionario = funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Dependente\nNome: " + this.nome + "\nSexo:" + this.sexo + "\nGrau de Parentesco: " + this.grauParentesco
				+ "\nFuncionario: " + this.funcionario + "\n";
	}

	@Override
	public String toJson() {
		String json = "{\"id\" : \"" + id + "\", \"nome\" : \"" + this.getNome() + "\", \"sexo\" : \"" + this.getSexo()
				+ "\", \"grauParentesco\" : \"" + this.getGrauParentesco() + "\", \"funcionario\" : \""
				+ this.getFuncionario() + "\"}";

		return json;
	}

	public static Dependente fromDocument(Document doc) {
		if (doc == null) {
			return null;
		}
		Dependente dependente = new Dependente();

		dependente.id = doc.getObjectId("_id");
		dependente.nome = doc.getString("nome");
		dependente.sexo = doc.getString("sexo");
		dependente.grauParentesco = doc.getString("grauParentesco");
		// dependente.funcionario = doc.get("funcionario", Funcionario.class);

		return dependente;
	}
}
