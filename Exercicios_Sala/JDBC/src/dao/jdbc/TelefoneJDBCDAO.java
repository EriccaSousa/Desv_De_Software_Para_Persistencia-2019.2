
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.qxd.persist.conteudo_6_jdbc.dao.TelefoneDAO;
import br.ufc.qxd.persist.conteudo_6_jdbc.model.Telefone;

public class TelefoneJDBCDAO extends GenericJDBCDAO<Telefone> implements TelefoneDAO{

	public TelefoneJDBCDAO() {
		super("telefone");
	}
	
	@Override
	protected Telefone fromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		int ddd = rs.getInt("ddd");
		int numero = rs.getInt("numero");
		Telefone telefone = new Telefone(id, ddd, numero, null);
		return telefone;
	}

	@Override
	protected PreparedStatement prepareToInsert(Telefone t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Telefone> findByClientId(int clienteId) {
		try {		
			List<Telefone> l = new ArrayList<Telefone>();

			String sql = "select * from telefone where cliente_id=?;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, clienteId);
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
	protected PreparedStatement prepareToUpdate(Telefone t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
