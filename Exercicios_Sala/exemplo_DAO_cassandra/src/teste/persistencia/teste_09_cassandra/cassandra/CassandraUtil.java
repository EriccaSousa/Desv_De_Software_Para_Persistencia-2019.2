package teste.persistencia.teste_09_cassandra.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;

public class CassandraUtil {
	
	private final static String DATABASE = "per"; 
	private static SimpleClient client = new SimpleClient(DATABASE);

	public static CqlSession getSession() {
		return client.getSession();
	}
	
	public static CqlSession getSession(ContactPoint... contactPoints) {
		return client.getSession(contactPoints);
	}
	
	public static void closeSession() {
		client.closeSession();
	}
}
