
public class Telefone extends Bean {

	private int ddd;
	private int numero;
	private Cliente cliente;
	
	public Telefone() {
		this(0, 0, 0, null);
	}
	
	public Telefone(int ddd, int numero, Cliente cliente) {
		this(0, ddd, numero, cliente);
		/*
		// MESMO QUE:
		this.ddd = ddd;
		this.numero = numero;
		this.cliente = cliente;
		*/
	}
	
	public Telefone(int id, int ddd, int numero, Cliente cliente) {
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.cliente = cliente;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", ddd=" + ddd + ", numero=" + numero + "]";
	}
}
