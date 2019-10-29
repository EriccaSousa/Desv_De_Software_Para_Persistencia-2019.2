package model;

public class Pesquisador extends Funcionario {
	private String areaAtuacao;
	private Projeto projeto;

	public Pesquisador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pesquisador(String nome, String endereco, String sexo, String dataAniversario, double salario,
			Departamento departamento, String areaAtuacao, Projeto projeto) {
		super(nome, endereco, sexo, dataAniversario, salario, departamento);
		this.areaAtuacao = areaAtuacao;
		this.projeto = projeto;
		// TODO Auto-generated constructor stub
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

}
