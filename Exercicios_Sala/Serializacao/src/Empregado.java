import java.io.Serializable;

public class Empregado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String endereco;
	private transient int cpf;
	private int numero;
	
	public Empregado() {}
	
	public Empregado(String nome, String endereco, int cpf, int numero) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.numero = numero;
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

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Empregado [nome=" + nome + ", endereco=" + endereco + ", cpf=" + cpf + ", numero=" + numero + "]";
	}
}
