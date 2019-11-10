import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Principal {
	
	public static final String ARQUIVO = "arquivos/empregado.obj"; // pode ser o caminho absoluto ou o relativo

	public static void main(String[] args) {
		// exemplo1SerializarESalvar();
		exemplo2LerEDeserializar();
	}
	
	public static void exemplo2LerEDeserializar() {
		try {
			InputStream is = new FileInputStream(ARQUIVO);
			ObjectInputStream ois = new ObjectInputStream(is);
			Empregado e = (Empregado) ois.readObject();
			System.out.println(e);
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void exemplo1SerializarESalvar() {
		Empregado e = new Empregado();
		e.setNome("João");
		e.setEndereco("Quixadá");
		e.setCpf(123);
		e.setNumero(526);
		try {
			OutputStream os = new FileOutputStream(ARQUIVO);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(e);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
	}
}










