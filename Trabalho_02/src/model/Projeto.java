package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projeto_id")
	private int id;
	private String nome;
	private String periodo;
	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
	@OneToMany(mappedBy = "projeto")
	private List<Pesquisador> pesquisadores;

	public Projeto() {

	}

	public Projeto(String nome, String periodo, Departamento departamento, List<Pesquisador> pesquisadores) {
		this(0, nome, periodo, departamento, pesquisadores);
	}

	public Projeto(int id, String nome, String periodo, Departamento departamento, List<Pesquisador> pesquisadores) {
		super();
		this.id = id;
		this.nome = nome;
		this.periodo = periodo;
		this.departamento = departamento;
		this.pesquisadores = pesquisadores;
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

	public List<Pesquisador> getPesquisadores() {
		return pesquisadores;
	}

	public void setPesquisadores(List<Pesquisador> pesquisadores) {
		this.pesquisadores = pesquisadores;
	}

	@Override
	public String toString() {
		return "Projeto\nId: " + this.id + "\nNome: " + this.nome + "\nPeríodo: " + this.periodo + "\nDepartamento: "
				+ this.departamento + "\n";
	}

}
