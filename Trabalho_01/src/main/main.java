package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONObject;
import org.json.XML;

import manipuladorDeArquivos.LeitorSAX;
import manipuladorDeArquivos.TransformerArquivos;

public class main {

	public static TransformerArquivos transformer = new TransformerArquivos();
	public static final String ARQUIVO_OUTPUT = "xml_geradoMatch.xml";
	public static final String ARQUIVO_OUTJSON = "atletas.json";

	public static void main(String[] args) {

		transformer.createXML();

		try {
			System.out.println("\nIniciando leitura do XML com SAX...");
			File inputFile = new File(ARQUIVO_OUTPUT);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			LeitorSAX userhandler = new LeitorSAX();
			saxParser.parse(inputFile, userhandler);

		} catch (Exception e) {
			System.out.println("Erro ao ler XML com SAX...");
			e.printStackTrace();
		}

		// Json
		try {
			System.out.println("\nGerando Json...");
			InputStream is = new FileInputStream(ARQUIVO_OUTPUT);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder builder = new StringBuilder();

			String aux;
			while ((aux = br.readLine()) != null) {
				builder.append(aux);
			}

			String xml = builder.toString();
			JSONObject jsonObj = XML.toJSONObject(xml);
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(ARQUIVO_OUTJSON));

			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < jsonObj.toString().split(",").length; i++) {
				bw.write(jsonObj.toString().split(",")[i]);
				if (i != jsonObj.toString().split(",").length - 1) {
					bw.write(",");
				}
				bw.write("\n");
			}

			bw.close();
			System.out.println("Json gerado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao gerar Json.");
			e.printStackTrace();
		}

	}

}
