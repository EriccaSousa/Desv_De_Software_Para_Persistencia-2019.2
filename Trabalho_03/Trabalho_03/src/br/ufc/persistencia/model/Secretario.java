package br.ufc.persistencia.model;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;

public class Secretario extends Funcionario {

	private String grauEscolaridade;

	public Secretario() {

	}

	public Secretario(String nome, String endereco, String sexo, Date dataAniver, double salario,
			List<Dependente> dependentes, Departamento departamento, String grauEscolaridade) {
		super(nome, endereco, sexo, dataAniver, salario, dependentes, departamento);
		this.grauEscolaridade = grauEscolaridade;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	@Override
	public String toString() {
		return "Secretario\n" + "\nid: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereco: "
				+ this.getEndereco() + "\nSexo: " + this.getSexo() + "\nData Aniversário: " + this.getDataAniver()
				+ "\nSalário: " + this.getSalario() + "\nGrau Escolaridade: " + this.grauEscolaridade
				+ "\nDepartamento: " + this.getDepartamento() + "\n";
	}

	@Override
	public String toJson() {

		String departamentoJson = getDepartamento().toJson();

		String json = "{\"id\":\"" + id + "\", \"nome\":\"" + getNome() + "\", \"endereco\":\"" + getEndereco()
				+ "\", \"sexo\":\"" + getSexo() + "\", \"dataAniver\":\"" + getDataAniver() + "\", \"salario\":\""
				+ getSalario() + "\", \"grauEscolaridade\":\"" + getGrauEscolaridade() + "\", \"departamento\":"
				+ departamentoJson + "\"}";
		return json;
	}

	public static Secretario fromDocument(Document doc) throws ParseException {
		if (doc == null) {
			return null;
		}
		Secretario secretario = new Secretario();
		secretario.id = doc.getObjectId("_id");
		secretario.setNome(doc.getString("nome"));
		secretario.setEndereco(doc.getString("endereco"));
		secretario.setSexo(doc.getString("sexo"));
		System.out.println(doc.getString("dataAniver"));
		secretario.setGrauEscolaridade(doc.getString("grauEscolaridade"));
		secretario.setDepartamento(doc.get("departamento", Departamento.class));
	

		return secretario;
	}
}
