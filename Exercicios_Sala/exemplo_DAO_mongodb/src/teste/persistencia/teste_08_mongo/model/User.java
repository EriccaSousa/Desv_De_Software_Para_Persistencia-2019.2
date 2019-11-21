package teste.persistencia.teste_08_mongo.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;
import org.bson.types.ObjectId;

public class User extends Bean {

	private String nome;
	private Set<String> emails;
	private List<String> topPlaces;
	private Map<LocalDateTime, String> coisasAFazer;
	
	public User() {
		this(null, "", new HashSet<>(), new ArrayList<>(), new HashMap<>());
	}
	
	public User(String nome) {
		this(null, nome, new HashSet<>(), new ArrayList<>(), new HashMap<>());
	}
	
	public User(ObjectId id, String nome) {
		this(id, nome, new HashSet<>(), new ArrayList<>(), new HashMap<>());
	}

	public User(ObjectId id, String nome, Set<String> emails, List<String> topPlaces, Map<LocalDateTime, String> coisasAFazer) {
		super(id);
		this.nome = nome;
		this.emails = emails;
		this.topPlaces = topPlaces;
		this.coisasAFazer = coisasAFazer;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<String> getEmails() {
		return emails;
	}

	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}

	public List<String> getTopPlaces() {
		return topPlaces;
	}

	public void setTopPlaces(List<String> topPlaces) {
		this.topPlaces = topPlaces;
	}

	public Map<LocalDateTime, String> getCoisasAFazer() {
		return coisasAFazer;
	}

	public void setCoisasAFazer(Map<LocalDateTime, String> coisasAFazer) {
		this.coisasAFazer = coisasAFazer;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", emails=" + emails + ", topPlaces=" + topPlaces
				+ ", coisasAFazer=" + coisasAFazer + "]";
	}
	
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
	public void addTopPlace(String topPlace) {
		this.topPlaces.add(topPlace);
	}
	
	public void addCoisaAFazer(LocalDateTime chave, String valor) {
		this.coisasAFazer.put(chave, valor);
	}
	
	public String toJson() {
		
		String emailsJson = "[  ";
		for(String email : emails) {
			emailsJson += "\"" + email + "\", ";
		}
		emailsJson = emailsJson.substring(0, emailsJson.length()-2);
		emailsJson += "]";
		
		String topPlacesJson = "[  ";
		for(String topPlace : topPlaces) {
			topPlacesJson += "\"" + topPlace + "\", ";
		}
		topPlacesJson = topPlacesJson.substring(0, topPlacesJson.length()-2);
		topPlacesJson += "]";
		
		String coisasAFazerJson = "{  ";
		
		for(Entry<LocalDateTime, String> coisa : coisasAFazer.entrySet()) {
			coisasAFazerJson += "\"" + coisa.getKey().toInstant(ZoneOffset.UTC).toEpochMilli() + "\" : \"" + coisa.getValue() + "\", ";
		}
		
		coisasAFazerJson = coisasAFazerJson.substring(0, coisasAFazerJson.length()-2);
		coisasAFazerJson += "}";
		
		String json = "{\"nome\" : \""+nome+"\", \"emails\" : "+emailsJson+", \"top_places\" : "+topPlacesJson+", \"coisas_a_fazer\" : "+coisasAFazerJson+"}";
		
		
		return json;
	}
	
	
	
	public static User fromDocument(Document doc) {
		if(doc == null) return null;
		User user = new User();
		
		user.id = doc.getObjectId("_id");
		user.nome = doc.getString("nome");
		user.emails = new HashSet<String>(doc.getList("emails", String.class));
		user.topPlaces = doc.getList("top_places", String.class);
		
		Document coisas = doc.get("coisas_a_fazer", Document.class);
		
		for(Entry<String, Object> entry : coisas.entrySet()) {
			LocalDateTime chave = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(entry.getKey())), ZoneOffset.UTC);
			user.addCoisaAFazer(chave, (String) entry.getValue());
		}
		
		return user;
	}
}







