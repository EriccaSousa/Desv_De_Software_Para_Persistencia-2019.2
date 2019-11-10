
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.ufc.qxd.persist.conteudo_6_jdbc.dao.ClienteDAO;
import br.ufc.qxd.persist.conteudo_6_jdbc.dao.EnderecoDAO;
import br.ufc.qxd.persist.conteudo_6_jdbc.dao.TelefoneDAO;
import br.ufc.qxd.persist.conteudo_6_jdbc.model.Cliente;
import br.ufc.qxd.persist.conteudo_6_jdbc.model.Endereco;
import br.ufc.qxd.persist.conteudo_6_jdbc.model.Telefone;

public class ClienteJDBCDAO extends GenericJDBCDAO<Cliente> implements ClienteDAO{
	
	public ClienteJDBCDAO() {
		super("cliente");
	}

	@Override
	protected Cliente fromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String nome = rs.getString("nome");
		String cpf = rs.getString("cpf");
		Cliente cliente = new Cliente(id, nome, cpf);
		
		TelefoneDAO tdao = new TelefoneJDBCDAO();
		List<Telefone> telefones = tdao.findByClientId(id);
		tdao.closeConnection();
		if(telefones != null) {
			cliente.setTelefones(telefones);
			for(Telefone t : telefones) t.setCliente(cliente);
		}
		
		EnderecoDAO edao = new EnderecoJDBCDAO();
		List<Endereco> enderecos = edao.findByClientId(id);
		edao.closeConnection();
		if(enderecos != null) cliente.setEnderecos(enderecos);
		
		return cliente;
	}

	@Override
	protected PreparedStatement prepareToInsert(Cliente t) throws SQLException {
		String sql = "INSERT INTO cliente VALUES (default, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, t.getNome());
		stmt.setString(2, t.getCpf());
		return stmt;
	}
	

	@Override
	protected PreparedStatement prepareToUpdate(Cliente t) throws SQLException {
		String sql = "UPDATE cliente SET nome=?, cpf=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, t.getNome());
		stmt.setString(2, t.getCpf());
		stmt.setInt(3, t.getId());
		return stmt;
	}
	
	@Override
	public void insert(Cliente c) {
		super.insert(c);
		TelefoneDAO tdao = new TelefoneJDBCDAO();
		for(Telefone t : c.getTelefones()) {
			if(t.getId()==0) tdao.insert(t);
		}
		tdao.closeConnection();
		
		EnderecoDAO edao = new EnderecoJDBCDAO();
		for(Endereco e : c.getEnderecos()) {
			if(e.getId()==0) edao.insert(e);
			String sql = "INSERT INTO cliente_endereco VALUES "
					+ "(?, ?)";
			PreparedStatement stmt;
			try {
				stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, c.getId());
				stmt.setInt(2, e.getId());
				stmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
		}
		edao.closeConnection();		
	}
}















