package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dependente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dependente_id")
	private int id;
	private String nome;
	private String dataAniver;
	private String grauParentesco;
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	public Dependente() {

	}

	public Dependente(String nome, String dataAniver, String grauParentesco, Funcionario funcionario) {
		this(0, nome, dataAniver, grauParentesco, funcionario);
	}

	public Dependente(int id, String nome, String dataAniver, String grauParentesco, Funcionario funcionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataAniver = dataAniver;
		this.grauParentesco = grauParentesco;
		this.funcionario = funcionario;
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

	public String getDataAniver() {
		return dataAniver;
	}

	public void setDataAniver(String dataAniver) {
		this.dataAniver = dataAniver;
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
		return "Dependente\nId: " + this.id + "\nNome: " + this.nome + "\nData Aniversário: " + this.dataAniver
				+ "\nGrau de Parentesco: " + this.grauParentesco + "\nInformeções do Funcionario agregado:\n "
				+ this.funcionario + "\n";
	}

}
