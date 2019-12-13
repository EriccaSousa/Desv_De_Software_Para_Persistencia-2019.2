package br.ufc.persistencia.model;

import java.util.List;

import org.bson.Document;


public class Projeto extends Bean{
	
	private String nome;
	private String periodo;
	private Departamento departamento;
	
	public Projeto() {
		
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}



	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		String json = "{\"id\" : \"" + id + "\", \"nome\" : \"" + this.getNome() + "\", \"periodo\" : \"" + this.getPeriodo()
		+ "\", \"departamento\" : \"" + this.getDepartamento() + "\"}";
		return json;
	}
	
	public static Projeto fromDocument(Document doc) {
		if (doc == null) {
			return null;
		}

		Projeto projeto = new Projeto();

		projeto.id = doc.getObjectId("_id");
		projeto.nome = doc.getString("nome");
		projeto.periodo = doc.getString("periodo");

		return projeto;
	}

}
