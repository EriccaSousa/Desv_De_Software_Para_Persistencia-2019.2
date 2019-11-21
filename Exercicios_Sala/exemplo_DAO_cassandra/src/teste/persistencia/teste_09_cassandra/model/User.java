package teste.persistencia.teste_09_cassandra.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.datastax.oss.driver.api.core.cql.Row;

public class User extends Bean {

	private String nome;
	private Set<String> emails;
	private List<String> topPlaces;
	private Map<Instant, String> coisasAFazer;
	
	public User() {
		this(0, "", new HashSet<>(), new ArrayList<>(), new HashMap<>());
	}
	
	public User(String nome) {
		this(0, nome, new HashSet<>(), new ArrayList<>(), new HashMap<>());
	}
	
	public User(int id, String nome) {
		this(id, nome, new HashSet<>(), new ArrayList<>(), new HashMap<>());
	}

	public User(int id, String nome, Set<String> emails, List<String> topPlaces, Map<Instant, String> coisasAFazer) {
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

	public Map<Instant, String> getCoisasAFazer() {
		return coisasAFazer;
	}

	public void setCoisasAFazer(Map<Instant, String> coisasAFazer) {
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
		Instant i = chave.toInstant(ZoneOffset.UTC);
		this.coisasAFazer.put(i, valor);
	}
	
	public static User fromRow(Row row) {
		if(row == null) return null;
		User user = new User();
		
		user.setId(row.getInt("id"));
		user.setNome(row.getString("nome"));
		user.setEmails(row.getSet("emails", String.class));
		user.setTopPlaces(row.getList("top_places", String.class));
		user.setCoisasAFazer(row.getMap("coisas_a_fazer", Instant.class, String.class));
		
		return user;
	}
}







