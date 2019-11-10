import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Principal {
	
	public static final String EXEMPLO_HTTP = "https://example.com";
	public static final String ARQUIVO_LOCAL = "arquivos/teste.html";
	
	public static void main(String[] args) {		
		// exemplo1ConexaoRemotaJsoup();
		exemplo2ConexaoLocalJsoup();
	}
	
	public static void exemplo2ConexaoLocalJsoup() {
		
		File file = new File(ARQUIVO_LOCAL);
		try {
			Document doc = Jsoup.parse(file, "UTF-8");
			Elements links = doc.select("a[href]");
			
			for(Element link : links) {
				System.out.println(link.attr("href"));
			}
			
			Elements imagens = doc.select("img[src$=.png]");
			for(Element imagem : imagens) {
				System.out.println(imagem);
			}
			
			Elements divs = doc.select("div.classe1");
			for(Element div : divs) {
				System.out.println(div);
			}
			
			// Element primeiraDivDaClasse2 = doc.selectFirst("div.classe2");
			Element primeiraDivDaClasse2 = doc.select("div.classe2").first();
			System.out.println(primeiraDivDaClasse2);
			
			Element div3 = doc.selectFirst("div#div3");
			String classeDiv3 = div3.attr("class");
			System.out.println(classeDiv3);
			
			Elements resultLinks = doc.select("h3.r > a");
			
			for(Element link : resultLinks) {
				System.out.println(link);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void exemplo1ConexaoRemotaJsoup() {
		try {
			Document doc = Jsoup.connect(EXEMPLO_HTTP).get();		
			
			String title = doc.title();
			
			System.out.println(title);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
