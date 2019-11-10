public class Principal {
	
	public static final String ARQUIVO_XML = "arquivos/livros.xml";

	public static void main(String[] args) {
		// parseWithDOM();
		parseWithSAX();
	}
	
	public static void parseWithDOM() {
		ParserDOM parser = new ParserDOM();
		parser.parse(ARQUIVO_XML);
		parser.printRaiz();
	}
	
	public static void parseWithSAX() {
		ParserSAX parser = new ParserSAX();
		parser.parse(ARQUIVO_XML);
	}
}











