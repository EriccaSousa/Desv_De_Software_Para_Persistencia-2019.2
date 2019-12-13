package br.ufc.persistencia.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Departamento extends Bean {

	private String nome;

	public Departamento() {

	}

	public Departamento(ObjectId id, String nome) {
		super(id);
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Departamento \nId: " + this.id + "\nNome: " + this.nome + "\n";
	}

	@Override
	public String toJson() {
		String json = "{\"id\":\"" + id + "\", \"nome\":\"" + nome + "\"}";

		return json;
	}

	public static Departamento fromDocument(Document doc) {
		if (doc == null) {
			return null;
		}

		Departamento departamento = new Departamento();

		departamento.id = doc.getObjectId("_id");
		departamento.nome = doc.getString("nome");

		return departamento;
	}
}
