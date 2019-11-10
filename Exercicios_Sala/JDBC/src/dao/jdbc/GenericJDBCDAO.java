
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.qxd.persist.conteudo_6_jdbc.connection.ConnectionFactory;
import br.ufc.qxd.persist.conteudo_6_jdbc.dao.GenericDAO;
import br.ufc.qxd.persist.conteudo_6_jdbc.model.Bean;

public abstract class GenericJDBCDAO<T extends Bean> implements GenericDAO<T>{
	
	protected Connection connection;
	protected String tableName;
	
	public GenericJDBCDAO(String tableName) {
		this.connection = ConnectionFactory.getConnection();
		this.tableName = tableName;
	}
	
	protected abstract T fromResultSet(ResultSet rs) throws SQLException;
	protected abstract PreparedStatement prepareToInsert(T t) throws SQLException;
	protected abstract PreparedStatement prepareToUpdate(T t) throws SQLException;
	
	@Override
	public void insert(T c) {
		try {
			PreparedStatement stmt = this.prepareToInsert(c);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) c.setId(rs.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(T c) {
		try {
			PreparedStatement stmt = this.prepareToUpdate(c);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) c.setId(rs.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(T c) {
		if(c == null) return false;
		return this.delete(c.getId());
	}

	@Override
	public boolean delete(int id) {
		try {
			String sql = "DELETE FROM " + tableName + " WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			int res = stmt.executeUpdate();
			if(res == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public T findById(int id) {
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			T t = null;
			if(rs.next()) t = this.fromResultSet(rs);
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<T> findAll() {
		try {		
			List<T> l = new ArrayList<T>();
			String sql = "SELECT * FROM " + tableName;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				l.add(this.fromResultSet(rs));
			}
			return l;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
