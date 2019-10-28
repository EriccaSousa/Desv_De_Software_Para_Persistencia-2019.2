package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Funcionario {

	@Id
	private String id;
	private String nome;
	private String endereco;
	private String sexo;
	private String dataAniversario;
	private double salario;
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private List<Dependente> dependentes;

	public Funcionario() {
		super();

	}

	public Funcionario(String id, String nome, String endereco, String sexo, String dataAniversario, double salario,
			List<Dependente> dependentes) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.sexo = sexo;
		this.dataAniversario = dataAniversario;
		this.salario = salario;
		this.dependentes = dependentes;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(String dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	@Override
	public String toString() {
		return "\nFuncionario\nId: " + this.id + "Nome: " + this.nome + "\nEndereco: " + this.endereco + "\nSexo: "
				+ this.sexo + "\nData Aniversario: " + this.dataAniversario + "Salario: " + this.salario + "\n";
	}

}
