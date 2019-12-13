package br.ufc.persistencia.model;

import org.bson.Document;

public class HorasTrabalhadas extends Bean{
	private String horasTrabalhadas;
	private Projeto projeto;
	private Pesquisador pesquisador;
	
	
	public String getHorasTrabalhadas() {
		return horasTrabalhadas;
	}


	public void setHorasTrabalhadas(String horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	@Override
	public String toJson() {
		String json = "{\"id\" : \"" + id + "\", \"Pesquisador\" : \"" + this.getPesquisador() + "\", "
				+ "\"Projeto\" : \"" + this.getProjeto() + "\"}";
		return json;
	}
	
	public static HorasTrabalhadas fromDocument(Document doc) {
		if (doc == null) {
			return null;
		}

		HorasTrabalhadas horastrabalhadas = new HorasTrabalhadas();

		horastrabalhadas.id = doc.getObjectId("_id");
		

		return horastrabalhadas;
	}
	
}