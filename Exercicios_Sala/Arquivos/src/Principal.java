import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

// OS EXEMPLOS FORAM DIVIDIDOS EM FUNÇÕES, CADA EXEMPLO UMA FUNÇÃO
public class Principal {

	// COLOCAR CAMINHOS DOS SEUS ARQUIVOS AQUI (podem ser caminhos absolutos):
	public static final String ARQUIVO = "arquivos/teste.txt";
	public static final String ARQUIVO_ZIP = "arquivos/teste.zip";
	public static final String ARQUIVO_MATRIZ = "arquivos/mySample.csv";
	public static final String ARQUIVO_MATRIZ_TRANSPOSTA = "arquivos/transposta.csv";
	
	
	public static void main(String[] args) {
		try {
			// exemplo1LerByte();
			// exemplo2LerCaracteres();
			// exemplo3LerStrings();
			// exemplo4LerArquivoComScanner();
			// exemplo5LerDoTecladoSemScanner();
			// exemplo6EscreverEmArquivo();
			// exemplo7EscreverEmArquivoComPrintStream();
			// exemplo8LerDoTecladoEEscreverEmArquivo();
			// exemplo9TryWithResources();
			// exemplo10Properties();
			// exemplo11SalvarProperties();
			// exemplo12LerProperties();
			// exemplo13LendoZip();
			String[][] mat = exemplo14LerMatriz();
			exemplo15EscreverMatriz(mat);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void exemplo15EscreverMatriz(String[][] mat) throws IOException {
		if(mat.length==0 || mat[0].length==0) return;
		OutputStream os = new FileOutputStream(ARQUIVO_MATRIZ_TRANSPOSTA); 
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		
		for(int j=0;j<mat[0].length;j++) {
			/*
			for(int i=0;i< mat.length - 1 ;i++) {
				bw.write(mat[i][j] + ",");
			}
			bw.write(mat[mat.length-1][j]);*/
			bw.write(mat[0][j]);
			for(int i=1;i< mat.length;i++) {
				bw.write("," + mat[i][j]);
			}
			bw.newLine();
		}
		
		bw.close();
	}
	
	public static String[][] exemplo14LerMatriz() throws IOException {
		InputStream is = new FileInputStream(ARQUIVO_MATRIZ);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		List<String[]> l = new ArrayList<String[]>();
		
		while(br.ready()) {
			String linha = br.readLine();
			String[] celulas = linha.split(",");
			l.add(celulas);
		}
		String[][] mat = l.toArray(new String[][] {});
		br.close();
		return mat;
	}
	
	public static void exemplo13LendoZip() throws IOException {
		InputStream is = new FileInputStream(ARQUIVO_ZIP);
		ZipInputStream zis = new ZipInputStream(is);
		InputStreamReader isr = new InputStreamReader(zis);
		BufferedReader br = new BufferedReader(isr);
		while(zis.getNextEntry() != null) {
			while(br.ready()) System.out.println(br.readLine());
		}
		br.close();
	}
	
	public static void exemplo12LerProperties() throws IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(ARQUIVO));
		
		String aluno = prop.getProperty("aluno");
		String faculdade = prop.getProperty("faculdade");
		double salario = Double.parseDouble(prop.getProperty("salario"));
		
		
		System.out.println(aluno);
		System.out.println(faculdade);
		System.out.println(salario);
	}
	
	public static void exemplo11SalvarProperties() throws IOException {
		Properties prop = new Properties();
		prop.setProperty("aluno", "luis");
		prop.setProperty("faculdade", "ufc");
		prop.setProperty("salario", "100000");
		prop.store(new FileOutputStream(ARQUIVO), null);
	}
	
	public static void exemplo10Properties() throws IOException {
		Properties prop = new Properties();
		prop.setProperty("aluno", "luis");
		prop.setProperty("faculdade", "ufc");
		prop.setProperty("salario", "100000");
			
		String aluno = prop.getProperty("aluno");
		String faculdade = prop.getProperty("faculdade");
		double salario = Double.parseDouble(prop.getProperty("salario"));
		String aux = prop.getProperty("aux");
		
		System.out.println(aluno);
		System.out.println(faculdade);
		System.out.println(salario);
		System.out.println(aux);
		
	}
	
	public static void exemplo9TryWithResources() throws IOException {
		InputStream is = new FileInputStream(ARQUIVO);
		InputStreamReader isr = new InputStreamReader(is);
		try(BufferedReader br = new BufferedReader(isr)){
			while(br.ready()) System.out.println(br.readLine());
		}
	}
	
	@SuppressWarnings("resource")
	public static void exemplo8LerDoTecladoEEscreverEmArquivo() throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintStream ps = new PrintStream(ARQUIVO);
		String s = sc.nextLine();
		while(!s.isEmpty()) {
			ps.println(s);
			s = sc.nextLine();
		}
		ps.close();
	}
	
	public static void exemplo7EscreverEmArquivoComPrintStream() throws IOException {
		PrintStream ps = new PrintStream(ARQUIVO);
		ps.println("Ronier é do pet");
		ps.close();
	}
	
	public static void exemplo6EscreverEmArquivo() throws IOException {
		/* 
		 * SE O SEGUNDO PARÂMETRO PASSADO PARA O CONSTRUTOR DO FileOutputStream FOR true, A ESCRITA SERÁ FEITA NO FIM DO ARQUIVO, MANTENDO OS DADOS EXISTENTES
		 * SE O SEGUNDO PARÂMETRO PASSADO PARA O CONSTRUTOR DO FileOutputStream FOR false OU NÃO FOR PASSADO,
		 * A ESCRITA SERÁ FEITA NO FIM DO ARQUIVO, MANTENDO OS DADOS EXISTENTES
		 */
		OutputStream os = new FileOutputStream(ARQUIVO, true);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		
		bw.write("Java");
		bw.newLine();
		
		bw.close();
	}
	
	
	public static void exemplo5LerDoTecladoSemScanner() throws IOException {
		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		while(!s.isEmpty()) {
			System.out.println(s);
			s = br.readLine();
		}
	}
	
	@SuppressWarnings("resource")
	public static void exemplo4LerArquivoComScanner() throws IOException {
		InputStream is = new FileInputStream(ARQUIVO);
		Scanner sc = new Scanner(is);
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			System.out.println(s);
		}
		is.close();
	}
	
	public static void exemplo3LerStrings() throws IOException {
		InputStream is = new FileInputStream(ARQUIVO);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		while(br.ready()) {
			String s = br.readLine();
			System.out.println(s);
		}
		br.close();
	}
	
	public static void exemplo2LerCaracteres() throws IOException {
		InputStream is = new FileInputStream(ARQUIVO);
		InputStreamReader isr = new InputStreamReader(is);
		
		// PODEMOS CONTINUAR LENDO BYTES COMO ABAIXO: 
		/*
		int b = isr.read();
		System.out.println(b);
		*/
		// OU LER VÁRIOS CARACTERES (USANDO UM BUFFER):
		while(isr.ready()) {
			char buffer[] = new char[5];
			isr.read(buffer);
			
			String s = new String(buffer);
			System.out.print(s);
		}
		isr.close();
	}
	
	public static void exemplo1LerByte() throws IOException {
		InputStream is = new FileInputStream(ARQUIVO);
		int b = is.read();
		System.out.println(b);
		is.close();
	}
}



