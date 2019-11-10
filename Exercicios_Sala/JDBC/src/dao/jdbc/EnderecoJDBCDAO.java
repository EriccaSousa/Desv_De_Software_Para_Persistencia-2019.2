import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.qxd.persist.conteudo_6_jdbc.dao.EnderecoDAO;
import br.ufc.qxd.persist.conteudo_6_jdbc.model.Endereco;

public class EnderecoJDBCDAO extends GenericJDBCDAO<Endereco> implements EnderecoDAO{

	public EnderecoJDBCDAO() {
		super("endereco");
	}
	
	@Override
	protected Endereco fromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String rua = rs.getString("rua");
		int numero = rs.getInt("numero");
		String bairro = rs.getString("bairro");
		String cidade = rs.getString("cidade");
		Endereco e = new Endereco(id, rua, numero, bairro, cidade, new ArrayList<>());
		return e;
	}

	@Override
	protected PreparedStatement prepareToInsert(Endereco t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Endereco> findByClientId(int clienteId) {
		try {		
			List<Endereco> l = new ArrayList<Endereco>();

			String sql = "SELECT e.* FROM endereco e, cliente_endereco ce"
					+ " WHERE ce.endereco_id=e.id AND ce.cliente_id=?;";
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
	protected PreparedStatement prepareToUpdate(Endereco t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
