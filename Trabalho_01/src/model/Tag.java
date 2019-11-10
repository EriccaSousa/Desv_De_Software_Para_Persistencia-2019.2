package model;

public class Tag {
	private String nome;
	private String valor;

	public Tag() {

	}

	public Tag(String nome, String valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public String getvalor() {
		return valor;
	}

	public void setvalor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Tag [nome=" + nome + ", valor=" + valor + "]";
	}

}