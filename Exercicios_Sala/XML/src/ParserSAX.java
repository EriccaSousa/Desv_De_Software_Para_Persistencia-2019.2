import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserSAX extends DefaultHandler{
	
	private int tabs = 0;
	
	public void parse(String filePath) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(filePath, this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Inicio de documento");
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("fim do documento");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		String s = "";
		for(int k=0;k<tabs;k++) s += "\t";
		System.out.println(s + "abrindo: " + qName);
		tabs++;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String s = "";
		for(int k=0;k<tabs;k++) s += "\t";
		System.out.println(s + "fechando: " + qName);
		tabs--;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = "\t";
		for(int k=0;k<tabs;k++) s += "\t";
		String text = new String(ch, start, length);
		if(!s.trim().isEmpty())
			System.out.println(s + "texto: " + text);
	}
}













