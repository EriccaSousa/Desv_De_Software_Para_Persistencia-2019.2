package teste.persistencia.teste_09_cassandra.dao.cassandra;

import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import teste.persistencia.teste_09_cassandra.dao.UserDAO;
import teste.persistencia.teste_09_cassandra.model.User;

public class UserCassadraDAO extends GenericCassandraDAO<User> implements UserDAO {

	public UserCassadraDAO() {
		super("users");
	}

	@Override
	public void insert(User user) {
		PreparedStatement stmt = session.prepare(""
				+ "INSERT INTO users "
				+ "(id, nome, emails, top_places, coisas_a_fazer) "
				+ "VALUES (?, ?, ?, ?, ?);");
		BoundStatement bound = stmt.bind(user.getId(), user.getNome(),
				user.getEmails(), user.getTopPlaces(), 
				user.getCoisasAFazer());
		session.execute(bound);
	}

	@Override
	public void update(User user) {
		PreparedStatement stmt = session.prepare(""
				+ "UPDATE users SET "
				+ "nome=?, emails=?, top_places=?, coisas_a_fazer=? "
				+ "WHERE id=?;");
		BoundStatement bound = stmt.bind(user.getNome(),
				user.getEmails(), user.getTopPlaces(), 
				user.getCoisasAFazer(), user.getId());
		session.execute(bound);
	}

	@Override
	public User find(Object id) {
		PreparedStatement stmt = session.prepare("SELECT * FROM users"
				+ " WHERE id=?; ");
		BoundStatement bound = stmt.bind(id);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		User user = User.fromRow(row);
		return user;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		String cql = "SELECT * FROM users;";
		ResultSet rs = session.execute(cql);
		List<Row> rows = rs.all();
		for(Row row : rows) {
			users.add(User.fromRow(row));
		}
		return users;
	}

}
