
import java.util.List;

import br.ufc.qxd.persist.conteudo_6_jdbc.model.Telefone;

public interface TelefoneDAO extends GenericDAO<Telefone> {
	
	public List<Telefone> findByClientId(int id);
}
