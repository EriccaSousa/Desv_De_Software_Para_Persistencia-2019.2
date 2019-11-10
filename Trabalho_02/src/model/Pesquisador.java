package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class Pesquisador extends Funcionario {

	private String areaAtuacao;
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	public Pesquisador() {

	}

	public Pesquisador(String nome, String endereco, String sexo, String dataAniversario, double salario,
			List<Dependente> dependentes, Departamento departamento, String areaAtuacao, Projeto projeto) {
		this(0, nome, endereco, sexo, dataAniversario, salario, dependentes, departamento, areaAtuacao, projeto);

	}

	public Pesquisador(int id, String nome, String endereco, String sexo, String dataAniversario, double salario,
			List<Dependente> dependentes, Departamento departamento, String areaAtuacao, Projeto projeto) {
		super(id, nome, endereco, sexo, dataAniversario, salario, dependentes, departamento);
		this.areaAtuacao = areaAtuacao;
		this.projeto = projeto;

	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public String toString() {
		return "Função: Pesquisador" + "\nId: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereço: "
				+ this.getEndereco() + "\nSexo: " + this.getSexo() + "\nData Aniversário: " + this.getDataAniversario()
				+ "\nSalário: " + this.getSalario() + "\nÁrea de atuação: " + this.areaAtuacao + "\nDepartamento: "
				+ this.getDepartamento() + "\nProjeto: " + this.projeto + "\n";
	}

}
