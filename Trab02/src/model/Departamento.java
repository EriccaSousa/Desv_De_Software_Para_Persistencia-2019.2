package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "departamento_id")
	private int id;
	private String nome;
	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
	private List<Projeto> projetos;

	public Departamento() {

	}

	public Departamento(String nome, List<Projeto> projetos) {
		this(0, nome, projetos);

	}

	public Departamento(int id, String nome, List<Projeto> projetos) {
		super();
		this.id = id;
		this.nome = nome;
		this.projetos = projetos;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
