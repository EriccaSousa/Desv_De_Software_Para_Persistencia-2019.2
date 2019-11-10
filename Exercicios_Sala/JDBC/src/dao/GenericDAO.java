
import java.util.List;

import br.ufc.qxd.persist.conteudo_6_jdbc.model.Bean;

public interface GenericDAO <T extends Bean> {
	
	public void insert(T c);
	public void update(T c);
	public boolean delete(T c);
	public boolean delete(int id);
	public T findById(int id);
	public List<T> findAll();
	public void closeConnection();

}
