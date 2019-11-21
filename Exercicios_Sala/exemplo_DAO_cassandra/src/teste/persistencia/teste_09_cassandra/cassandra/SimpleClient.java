package teste.persistencia.teste_09_cassandra.cassandra;

import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;

import teste.persistencia.teste_09_cassandra.util.Util;

public class SimpleClient {

	private CqlSession session;
	private String database;
	private ContactPoint[] contatcPoints;
	
	public SimpleClient() {
		this(null);
	}
	
	public SimpleClient(String database) {
		this.database = database;
		this.session = null;
		this.contatcPoints = null;
	}
	
	private CqlSession createSession(ContactPoint[] contactPoints) {
		if(!Util.equalsArrays(contactPoints, this.contatcPoints)) {
			closeSession();
			this.contatcPoints = contactPoints;
		}
		if(session == null) {
			CqlSessionBuilder builder = CqlSession.builder();
			if(contatcPoints != null) {
				for(ContactPoint contatcPoint : this.contatcPoints) {
					builder.addContactPoint(new InetSocketAddress(contatcPoint.getIp(), contatcPoint.getPort()));
				}
			}
			session = builder.build();
		}
		if(database != null) session.execute("use " + database);
		return session;
	}
	
	public CqlSession getSession() {
		return createSession(null);
	}
	
	public CqlSession getSession(ContactPoint... contactPoints) {
		return createSession(contactPoints);
	}
	
	public void closeSession() {
		if(session != null) session.close();
		session = null;
	}
}








