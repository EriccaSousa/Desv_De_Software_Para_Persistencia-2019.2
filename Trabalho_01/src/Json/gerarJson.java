package Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.XML;
import org.json.simple.JSONObject;

public class gerarJson {
	static Map<String,String> example = new HashMap<String,String>();
	static Map<String, Map<String,String>> Atletas = new HashMap<String, Map<String, String>>();
	static Map<String, Map<String, Map<String,String>>> games = new HashMap<String, Map<String, Map<String,String>>>();
	public static int INDENTATION = 4;
	public static String XML_STRING =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<root>" +
				"<firstName>John</firstName>" +
				"<lastName>Snow</lastName>" +
				"<age>25</age>" +
				"<spouse/>" +
				"<address>" +
					"<street>237 Harrison Street</street>" +
					"<city>Brooklyn, NY</city>" +
					"<state>New York</state>" +
					"<postalCode>11238</postalCode>" +
				"</address>" +
				"<phoneNumbers>" +
					"<type>mobile</type>" +
					"<number>212 555-3346</number>" +
				"</phoneNumbers>" +
				"<phoneNumbers>" +
					"<type>fax</type>" +
					"<number>646 555-4567</number>" +
				"</phoneNumbers>" +
			"</root>";
	
	public gerarJson(String xml) {
		this.XML_STRING = xml;
	}
	
	public void gerarXML() {
		
		try {
			org.json.JSONObject jsonObj = XML.toJSONObject(XML_STRING);
			String json1 = jsonObj.toString(INDENTATION);
			System.out.println(json1);
			try {
				FileWriter file = new  FileWriter("atletas.json");
				file.write(json1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JSONException ex) {
			ex.printStackTrace();
		}	
	}

}
