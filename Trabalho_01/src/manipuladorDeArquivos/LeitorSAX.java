package manipuladorDeArquivos;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LeitorSAX extends DefaultHandler {

	boolean name = false;
	boolean sex = false;
	boolean age = false;
	boolean height = false;
	boolean weight = false;
	boolean team = false;
	boolean city = false;
	boolean sport = false;
	boolean event = false;
	boolean medal = false;
	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	String valor = null;
	
	public void xml_json(String tagvalor) {
		this.xml += "" + tagvalor;
	}
	
	public String getxml() {
		return this.xml;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("Atletas")) {
			String rollNo = attributes.getValue("id");
			System.out.println("\nGames Id : " + rollNo);
			
		} else if (qName.equalsIgnoreCase("Name")) {
			name = true;
	
		} else if (qName.equalsIgnoreCase("Sex")) {
			sex = true;
			
		} else if (qName.equalsIgnoreCase("Age")) {
			age = true;
			
		} else if (qName.equalsIgnoreCase("Height")) {
			height = true;
			
		} else if (qName.equalsIgnoreCase("Weight")) {
			weight = true;
			
		} else if (qName.equalsIgnoreCase("Team")) {
			team = true;
			
		} else if (qName.equalsIgnoreCase("City")) {
			city = true;
			
		} else if (qName.equalsIgnoreCase("Sport")) {
			sport = true;
			
		} else if (qName.equalsIgnoreCase("Event")) {
			event = true;
			
		} else if (qName.equalsIgnoreCase("Medal")) {
			medal = true;
			
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("Atletas")) {
			System.out.println("Fim atletas");
			
		}
	}
	
	public void pegar_valores(String valor) {
		this.valor = valor;
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		String valor = new String(ch, start, length);
		if (name) {
			System.out.println("Nome: " + valor);
			name = false;
		} else if (sex) {
			System.out.println("Sex: " + valor);
			sex = false;
		} else if (age) {
			System.out.println("Age: " + valor);
			age = false;
		} else if (height) {
			System.out.println("Height: " + valor);
			height = false;
		} else if (weight) {
			System.out.println("Weight: " + valor);
			weight = false;
		} else if (team) {
			System.out.println("Team: " + valor);
			team = false;
		} else if (city) {
			System.out.println("City: " + valor);
			city = false;
		}else if (sport) {
			System.out.println("Sport: " + valor);
			sport = false;
		} else if (event) {
			System.out.println("Event: " + valor);
			event = false;
		} else if (medal) {
			System.out.println("Medal: " + valor);
			medal = false;
		}
	}
}