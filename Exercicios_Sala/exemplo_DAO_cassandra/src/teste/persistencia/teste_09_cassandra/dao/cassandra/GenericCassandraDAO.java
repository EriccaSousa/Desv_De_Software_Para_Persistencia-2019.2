package teste.persistencia.teste_09_cassandra.dao.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;

import teste.persistencia.teste_09_cassandra.cassandra.CassandraUtil;
import teste.persistencia.teste_09_cassandra.dao.GenericDAO;
import teste.persistencia.teste_09_cassandra.model.Bean;

public abstract class GenericCassandraDAO<T extends Bean> implements GenericDAO<T>{

	private String tableName;
	protected CqlSession session;

	
	public GenericCassandraDAO(String tableName) {
		this.tableName = tableName;
		this.session = CassandraUtil.getSession();
	}
	
	@Override
	public void delete(T t) {
		delete(t.getId());
	}
	
	@Override
	public void delete(Object id) {
		PreparedStatement stmt = session.prepare(
				"DELETE FROM " + tableName + " WHERE id=?;");
		BoundStatement bound = stmt.bind(id);
		session.execute(bound);
	}
	
	@Override
	public void close() {
		CassandraUtil.closeSession();
	}
	
}
