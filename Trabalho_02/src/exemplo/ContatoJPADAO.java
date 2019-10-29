package exemplo;
public class ContatoJPADAO extends GenericJPADAO<Contato> implements ContatoDAO {

	public ContatoJPADAO() {
		super(Contato.class);
	}

}
