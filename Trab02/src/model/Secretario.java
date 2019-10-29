package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

public class Secretario extends Funcionario {

	private String grauEscolaridade;

	public Secretario() {

	}

	public Secretario(String nome, String endereco, String sexo, String dataAniversario, double salario
			, String grauEscolaridade, Departamento departamento) {
		super(nome, endereco, sexo, dataAniversario, salario, departamento);
		this.grauEscolaridade = grauEscolaridade;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	@Override
	public String toString() {
		return "Função: Secretario" + "\nId: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereço: "
				+ this.getEndereco() + "\nSexo: " + this.getSexo() + "\nData Aniversário: " + this.getDataAniversario()
				+ "\nSalário: " + this.getSalario() + "\ngrauEscolaridade: " + grauEscolaridade + "\n";
	}

}
