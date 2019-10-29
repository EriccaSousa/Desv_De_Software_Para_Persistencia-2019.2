package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class Pesquisador extends Funcionario {

	private String areaAtuacao;

	public Pesquisador() {

	}

	public Pesquisador(String nome, String endereco, String sexo, String dataAniversario, double salario,
			String areaAtuacao) {
		this(0, nome, endereco, sexo, dataAniversario, salario, areaAtuacao);

	}

	public Pesquisador(int id, String nome, String endereco, String sexo, String dataAniversario, double salario,
			String areaAtuacao) {
		super(id, nome, endereco, sexo, dataAniversario, salario);
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
				+ "\nSalário: " + this.getSalario() + "\nÁrea de atuação: " + this.areaAtuacao + "\n";
	}

}
