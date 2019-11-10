
import java.util.List;

import br.ufc.qxd.persist.conteudo_6_jdbc.model.Endereco;

public interface EnderecoDAO extends GenericDAO<Endereco> {
	
	public List<Endereco> findByClientId(int id);
}
