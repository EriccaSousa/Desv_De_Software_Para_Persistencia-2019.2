package manipuladorDeArquivos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.ObjetoXML;
import model.ParserDOM;
import model.ParserSAX;
import model.Tag;

public class TransformerArquivos {

	public static final String ARQUIVO_INPUT = "testeatletas.csv";
	public static final String ARQUIVO_OUTPUT = "xml_geradoMatch.xml";

	public LeitorDeArquivos lerAquivos = new LeitorDeArquivos();
	public List<ObjetoXML> objetoXML = new ArrayList<ObjetoXML>();

	public void createXML() {
		try {
			System.out.println("Gerando documento XML, aguarde...");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// Criando tag raiz;
			Element root = doc.createElement("Olimpíadas");
			doc.appendChild(root);

			//Criando elementos;
			criarElemento1(addObjXML(lerAquivos.lerCSV(ARQUIVO_INPUT)), root, doc);

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer optimusPrime = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(ARQUIVO_OUTPUT);
			optimusPrime.setOutputProperty(OutputKeys.INDENT, "yes");
			optimusPrime.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			optimusPrime.transform(source, result);
			
			System.out.println("XML gerado com sucesso!");
		} catch (ParserConfigurationException | TransformerException e) {
			System.out.println("Erro ao gerar XML!");
			e.printStackTrace();
		}
	}

	public String[] pegarTags(List<String[]> dadosCSV) {
		String[] tags = dadosCSV.get(0);
		dadosCSV.remove(0);

		return tags;
	}

	public String[] pegaValor(List<String[]> dadosCSV) {
		String[] valor = new String[dadosCSV.size()];

		for (int i = 0; i < dadosCSV.size(); i++) {
			valor = dadosCSV.get(i);
		}
		
		return valor;
	}

	public List<ObjetoXML> criarTag(String[] tags, String[] valor) {
		List<Tag> tagList = new ArrayList<Tag>();
		List<ObjetoXML> obj = new ArrayList<ObjetoXML>();

		for (int i = 0; i < tags.length; i++) {
			Tag newTag = new Tag(tags[i], valor[i]);
			tagList.add(newTag);
		}
		ObjetoXML objXML = new ObjetoXML(tagList);

		obj.add(objXML);

		return obj;

	}

	public List<ObjetoXML> addObjXML(List<String[]> dadosCSV) {
		String[] tagsList = pegarTags(dadosCSV);

		List<ObjetoXML> objetoXML = new ArrayList<>();

		for (String[] dados : dadosCSV) {
			int i = 0;
			List<Tag> tags = new ArrayList<>();

			for (String tag : tagsList) {
				tags.add(new Tag(tag, dados[i]));
				i++;
			}

			objetoXML.add(new ObjetoXML(tags));
		}
		return objetoXML;

	}

	public void criarElemento(List<ObjetoXML> objXML, Element elementRoot, Document doc) {
		Element atleta = null;

		for (ObjetoXML obj : objXML) {
			atleta = doc.createElement("Atletas");
			for (int i = 0; i < obj.getTags().size(); i++) {
				Element element = doc.createElement(obj.getTags().get(i).getnome());

				if (obj.getTags().get(i).getnome().equalsIgnoreCase("id")) {
					Attr attr = doc.createAttribute("id");
					attr.setValue(obj.getTags().get(i).getvalor());
					atleta.setAttributeNode(attr);
				} else {
					element.appendChild(doc.createTextNode(obj.getTags().get(i).getvalor()));
					atleta.appendChild(element);
				}
			}
			elementRoot.appendChild(atleta);
		}
	}
	
	public void criarElemento1(List<ObjetoXML> objXML, Element elementRoot, Document doc) {
		Element Games = null;
		Element atleta = null;
		for (ObjetoXML obj : objXML) {
			Games = doc.createElement("Season");
			Attr attrg = doc.createAttribute("Games");
			attrg.setValue(obj.getTags().get(7).getvalor());
			Games.setAttributeNode(attrg);
			atleta = doc.createElement("Atletas");
			for (int i = 0; i < obj.getTags().size(); i++) {
				Element element = null;
				if(!(obj.getTags().get(i).getnome().equalsIgnoreCase("Games"))) {
					//System.out.println(" ++++++++++++ " + obj.getTags().get(i).getnome());
					element = doc.createElement(obj.getTags().get(i).getnome());
					if (obj.getTags().get(i).getnome().equalsIgnoreCase("id") && !(obj.getTags().get(i).getnome() == "Games")) {
						Attr attr = doc.createAttribute("id");
						attr.setValue(obj.getTags().get(i).getvalor());
						atleta.setAttributeNode(attr);
					} else if(obj.getTags().get(i).getnome() != "Games"){
						element.appendChild(doc.createTextNode(obj.getTags().get(i).getvalor()));
						atleta.appendChild(element);
					}
				}
			}
			Games.appendChild(atleta);
			elementRoot.appendChild(Games);
		}
	}

	public void parseWithDOM() {
		ParserDOM parser = new ParserDOM();
		parser.parse(ARQUIVO_INPUT);
		parser.printRaiz();
	}

	public void parseWithSAX() {
		ParserSAX parser = new ParserSAX();
		parser.parse(ARQUIVO_INPUT);
	}
}
