package model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParserDOM {

	private Document doc;

	public void parse(String filePath) {
		try {
			File xmlFile = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(xmlFile);
			doc.normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private void printNo(Node no, int nivel) {
		String s = "";
		for (int k = 0; k < nivel; k++)
			s += "   ";

		if (no.getNodeType() == Node.TEXT_NODE) {
			if (!no.getNodeValue().trim().isEmpty())
				System.out.println(s + no.getNodeValue());
		} else {
			System.out.print(s + no.getNodeName());
			NamedNodeMap attrs = no.getAttributes();
			int numeroDeAtributos = attrs.getLength();
			for (int k = 0; k < numeroDeAtributos; k++) {
				Node n = attrs.item(k);
				System.out.print(" " + n.getNodeName() + "=");
				System.out.print(n.getNodeValue());
			}
			System.out.println();
			NodeList filhos = no.getChildNodes();
			int numeroDeFilhos = filhos.getLength();
			for (int k = 0; k < numeroDeFilhos; k++) {
				Node filho = filhos.item(k);
				printNo(filho, nivel + 1);
			}
		}
	}

	public void printNo(Node no) {
		printNo(no, 0);
	}

	public void printRaiz() {
		this.printNo(this.doc.getDocumentElement());

	}
}