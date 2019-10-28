package model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Dependente {
	/*
	 * nome, o sexo e a data de aniversário, além do grau de parentesco com o
	 * funcionário.
	 */

	@Id
	private String id;
	private String nome;
	private String dataAniver;
	private String grauParentesco;
	@ManyToOne
	private Funcionario funcionario;

	public Dependente() {

	}

	public Dependente(String id, String nome, String dataAniver, String grauParentesco, Funcionario funcionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataAniver = dataAniver;
		this.grauParentesco = grauParentesco;
		this.funcionario = funcionario;
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
