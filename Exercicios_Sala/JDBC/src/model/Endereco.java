
import java.util.ArrayList;
import java.util.List;

public class Endereco extends Bean {

	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private List<Cliente> clientes;
	
	public Endereco() {
		this(0, null, 0, null, null, new ArrayList<>());
	}
	
	public Endereco(String rua, int numero, String 
			bairro, String cidade) {
		this(0, rua, numero, bairro, cidade, new ArrayList<>());
	}
	
	public Endereco(int id, String rua, int numero, String bairro, String cidade, List<Cliente> clientes) {
		super(id);
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.clientes = clientes;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade="
				+ cidade + "]";
	}
}
