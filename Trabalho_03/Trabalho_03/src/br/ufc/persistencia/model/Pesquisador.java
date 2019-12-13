package br.ufc.persistencia.model;

import java.util.Date;
import java.util.List;

import org.bson.Document;

import br.ufc.persistencia.util.DataUtil;

public class Pesquisador extends Funcionario {

	private String areaAtuacao;

	public Pesquisador() {

	}

	public Pesquisador(String nome, String endereco, String sexo, Date dataAniver, double salario,
			List<Dependente> dependentes, Departamento departamento, String areaAtuacao) {
		super(nome, endereco, sexo, dataAniver, salario, dependentes, departamento);
		this.areaAtuacao = areaAtuacao;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}


	@Override
	public String toString() {
		return "Pesquisador" + "\nId: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereço: "
				+ this.getEndereco() + "\nSexo: " + this.getSexo() + "\nData Aniversário: " + this.getDataAniver()
				+ "\nSalário: " + this.getSalario() + "\nÁrea de atuação: " + this.areaAtuacao + "\nDepartamento: "
				+ this.getDepartamento() + "\n";
	}

	@Override
	public String toJson() {
		/*
		 * String projetosJson = "[ "; for (Projeto projeto : this.getProjetos()) {
		 * projetosJson += "\"" + projeto + "\", "; }
		 * 
		 * projetosJson = projetosJson.substring(0, projetosJson.length() - 2);
		 * projetosJson += "]";
		 */
		String json = "{\"id\" : \"" + id + "\", \"nome\" : \"" + getNome() + "\", \"endereco\" : \"" + getEndereco()
				+ "\", \"sexo\" : \"" + getSexo() + "\", \"dataAniver\" : \"" + getDataAniver() + "\", \"salario\" : \""
				+ getSalario() + "\", \"areaAtuacao\" : \"" + getAreaAtuacao() + "\"}";

		return json;
	}

	public static Pesquisador fromDocument(Document doc) {
		if (doc == null) {
			return null;
		}
		Pesquisador pesquisador = new Pesquisador();

		pesquisador.id = doc.getObjectId("_id");
		pesquisador.setNome(doc.getString("nome"));
		pesquisador.setEndereco(doc.getString("endereco"));
		pesquisador.setSexo(doc.getString("sexo"));
		System.out.println(doc.getString("dataAniver"));
		// pesquisador.setSalario(doc.getDouble("salario"));
		pesquisador.setAreaAtuacao(doc.getString("areaAtuacao"));
		// pesquisador.setDepartamento(doc.get("departamento", Departamento.class));
		// pesquisador.projetos = doc.getList("projetos", Projeto.class);
		// pesquisador.setDependentes(doc.getList("dependentes", Dependente.class));

		return pesquisador;
	}

}
