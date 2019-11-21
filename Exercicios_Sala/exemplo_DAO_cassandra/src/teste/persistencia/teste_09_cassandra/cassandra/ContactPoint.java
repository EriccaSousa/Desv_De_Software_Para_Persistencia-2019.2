package teste.persistencia.teste_09_cassandra.cassandra;

public class ContactPoint {

	private String ip;
	private int port;
	
	public ContactPoint(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ContactPoint [ip=" + ip + ", port=" + port + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof ContactPoint)) return false;
		ContactPoint other = (ContactPoint) obj;
		return this.port==other.port && this.ip.equals(other.ip);
	}
}



