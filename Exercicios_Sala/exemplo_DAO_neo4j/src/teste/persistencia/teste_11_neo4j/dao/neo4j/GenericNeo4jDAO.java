package teste.persistencia.teste_11_neo4j.dao.neo4j;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import teste.persistencia.teste_11_neo4j.dao.GenericDAO;
import teste.persistencia.teste_11_neo4j.model.Bean;
import teste.persistencia.teste_11_neo4j.neo4j.Neo4jUtil;

public abstract class GenericNeo4jDAO<T extends Bean> implements GenericDAO<T> {
	
	protected Session session;
	
	public GenericNeo4jDAO() {
		this.session = Neo4jUtil.getSession();
	}

	@Override
	public void delete(T t) {
		delete(t.getId());
	}

	protected StatementResult transaction(String operation) {
		StatementResult res = session.writeTransaction(new TransactionWork<StatementResult>() {
			@Override
			public StatementResult execute(Transaction tx) {
				StatementResult result = tx.run(operation);
				return result;
			}
		});
		return res;
	}

	@Override
	public void close() {
		Neo4jUtil.closeSession(this.session);
	}

}
