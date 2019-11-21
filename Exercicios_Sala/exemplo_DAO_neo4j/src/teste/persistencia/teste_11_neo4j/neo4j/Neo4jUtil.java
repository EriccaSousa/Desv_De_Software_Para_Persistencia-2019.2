package teste.persistencia.teste_11_neo4j.neo4j;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class Neo4jUtil {

	private static final String URI = "bolt://localhost:7687";
	private static final String DB_USER = "neo4j";
	private static final String DB_PASSWORD = "Neo4j";
	
	private static Set<Session> sessions = new HashSet<Session>();
	
	private static Driver driver = null;
	
	public static Session getSession() {
		if(driver == null) {
			driver = GraphDatabase.driver(URI, AuthTokens.basic(DB_USER, DB_PASSWORD));
		}
		Session session = driver.session();
		sessions.add(session);
		return session;
	}
	
	public static void closeSession(Session session) {
		if(session == null) return;
		sessions.remove(session);
		session.close();
		if(sessions.isEmpty()) {
			driver.close();
			driver = null;
		}		
	}
}
