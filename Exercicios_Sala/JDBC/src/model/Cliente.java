
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Bean {

	private String nome;
	private String cpf;
	private List<Telefone> telefones;
	private List<Endereco> enderecos;
	
	public Cliente() {
		this(0, null, null, new ArrayList<>(), new ArrayList<>());
	}
	
	public Cliente(String nome, String cpf) {
		this(0, nome, cpf, new ArrayList<>(), new ArrayList<>());
	}
	
	public Cliente(int id, String nome, String cpf) {
		this(id, nome, cpf, new ArrayList<>(), new ArrayList<>());
	}
	
	public Cliente(int id, String nome, String cpf, List<Telefone> telefones, List<Endereco> enderecos) {
		super(id);
		this.nome = nome;
		this.cpf = cpf;
		this.telefones = telefones;
		this.enderecos = enderecos;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefones=" + telefones + ", enderecos="
				+ enderecos + "]";
	}
}
