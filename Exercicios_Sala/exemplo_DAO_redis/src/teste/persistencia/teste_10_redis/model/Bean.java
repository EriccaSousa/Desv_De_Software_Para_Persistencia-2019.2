package teste.persistencia.teste_10_redis.model;

public abstract class Bean {

	protected int id;
	
	public Bean() {}
	
	public Bean(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
