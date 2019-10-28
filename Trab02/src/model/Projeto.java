package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Projeto.findAll", query = "from Projeto") })
public class Projeto {

	@Id
	private String id;
	private String nome;
	private String periodo;
	@ManyToOne
	private Departamento departamento;

	public Projeto() {

	}

	public Projeto(String id, String nome, String periodo, Departamento departamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.periodo = periodo;
		this.departamento = departamento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public String toString() {
		return "Projeto\nId: " + this.id + "\nNome: " + this.nome + "\nPeríodo: " + this.periodo + "\nDepartamento: "
				+ this.departamento + "\n";
	}

}
