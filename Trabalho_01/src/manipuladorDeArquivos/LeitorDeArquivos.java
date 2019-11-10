package manipuladorDeArquivos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.ObjetoXML;
import model.Tag;

public class LeitorDeArquivos {

	public List<String[]> lerCSV(String path) {
		try {
			System.out.println("Lendo documento " + path + ".");
			
			InputStream is = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			List<String[]> dadosCSV = new ArrayList<>();

			while (br.ready()) {
				String linha = br.readLine();
				String[] dados = linha.split(",");
				dadosCSV.add(dados);
			}
			br.close();

			System.out.println("Leitura do documento " + path + "realizada com sucesso!");
			return dadosCSV;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao ler documento " + path + ".");
		}
		return null;
	}
}
