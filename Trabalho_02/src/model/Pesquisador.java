package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class Pesquisador extends Funcionario {

	private String areaAtuacao;

	public Pesquisador() {

	}

	public Pesquisador(String nome, String endereco, String sexo, String dataAniversario, double salario,
			List<Dependente> dependentes, Departamento departamento, String areaAtuacao) {
		this(0, nome, endereco, sexo, dataAniversario, salario, dependentes, departamento, areaAtuacao);

	}

	public Pesquisador(int id, String nome, String endereco, String sexo, String dataAniversario, double salario,
			List<Dependente> dependentes, Departamento departamento, String areaAtuacao) {
		super(id, nome, endereco, sexo, dataAniversario, salario, dependentes, departamento);
		this.areaAtuacao = areaAtuacao;

	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	@Override
	public String toString() {
		return "Função: Pesquisador" + "\nId: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereço: "
				+ this.getEndereco() + "\nSexo: " + this.getSexo() + "\nData Aniversário: " + this.getDataAniversario()
				+ "\nSalário: " + this.getSalario() + "\nÁrea de atuação: " + this.areaAtuacao + "\nDepartamento: "
				+ this.getDepartamento();
	}

}
