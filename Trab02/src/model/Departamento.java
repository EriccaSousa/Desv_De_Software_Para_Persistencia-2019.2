package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {

	@Id
	private String id;
	private String nome;
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
	private List<Projeto> projetos;

	public Departamento() {

	}

	public Departamento(String id, String nome, List<Projeto> projetos) {
		super();
		this.id = id;
		this.nome = nome;
		this.projetos = projetos;
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

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	@Override
	public String toString() {
		return "Departamento\nid: " + this.id + "\nNome: " + this.nome;
	}

}
