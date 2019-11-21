package teste.persistencia.teste_11_neo4j.dao.neo4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import teste.persistencia.teste_11_neo4j.dao.UserDAO;
import teste.persistencia.teste_11_neo4j.model.User;

public class UserNeo4jDAO extends GenericNeo4jDAO<User> implements UserDAO {

	@Override
	public void insert(User user) {
		String operation = "CREATE (u:User) SET u.nome = '"+user.getNome()+"' ";

		// exemplo de coleção embutida no mesmo nó (se puder elementos repetidos, não faz sentido ser relacionamento)
		operation += ", u.top_places = [  ";
		for(String topPlace : user.getTopPlaces()) {
			operation += "'"+topPlace+"', ";
		}
		operation = operation.substring(0, operation.length()-2);
		operation += "] ";

		// exemplo de coleções relacionadas ao nó (só faz sentido se os relacionamentos forem diferentes)
		if(!user.getEmails().isEmpty() || !user.getCoisasAFazer().isEmpty()) {
			operation += "WITH u CREATE";

			for(String email : user.getEmails()) {
				operation += " (u)-[:email]->(:email{email:'"+email+"'}),";
			}

			for(Entry<LocalDateTime, String> entry : user.getCoisasAFazer().entrySet()) {
				operation += " (u)-[:coisa_a_fazer{chave:'"+entry.getKey()+"'}]"
						+ "->(:coisa_a_fazer{coisa_a_fazer:'"+entry.getValue()+"'}),";
			}
			operation = operation.substring(0, operation.length()-1);
		}
		operation += " RETURN id(u)";
		
		Record result = this.transaction(operation).single(); // só funciona se o return retornar realmente só um elemento
		int id = result.get(0).asInt();
		user.setId(id);
	}

	@Override
	public void update(User user) {
		
		
		String operation = "MATCH (u:User) WHERE id(u)="+user.getId()+" "+
						   "SET u.nome='"+user.getNome()+"', u.top_places=[  ";
		for(String topPlace : user.getTopPlaces()) {
			operation += "'"+topPlace+"', ";
		}
		operation = operation.substring(0, operation.length()-2);
		operation += "] ";
		this.transaction(operation);
		
		StatementResult aux = null;
		
		
		String findAntigosEmails = "MATCH (u:User)-[e:email]->(x) WHERE id(u)="+user.getId()+" RETURN x"; 
		aux = this.transaction(findAntigosEmails);
		
		List<String> antigosEmails = new ArrayList<String>();
		while(aux.hasNext()) {
			Record rec = aux.next();
			antigosEmails.add(rec.get(0).asNode().get("email").asString());
		}
		
		String novosEmails = "[  ";
		for(String email : user.getEmails()) {
			novosEmails += "'"+email + "', ";
			if(!antigosEmails.contains(email)) {
				this.transaction("MATCH (u:User) WHERE id(u)="+user.getId()+
								" CREATE (u)-[:email]->(:email{email:'"+email+"'})"); 
			}
		}
		novosEmails = novosEmails.substring(0, novosEmails.length()-2);
		novosEmails += "]";
		
		String deleteEmails = "MATCH (u:User)-[e:email]->(x) WHERE id(u)="+user.getId()+" AND NOT x.email IN "+novosEmails+" DELETE e";
		this.transaction(deleteEmails);
		
		
		String findAntigasChavesDeCoisasAFazer = "MATCH (u:User)-[c:coisa_a_fazer]->(x) WHERE id(u)="+user.getId()+" RETURN c"; 
		aux = this.transaction(findAntigasChavesDeCoisasAFazer);
		
		List<String> antigasChavesDeCoisasAFazer = new ArrayList<String>();
		while(aux.hasNext()) {
			Record rec = aux.next();
			antigasChavesDeCoisasAFazer.add(rec.get(0).asRelationship().get("chave").asString());
		}
		
		String novasChavesCoisasAFazer = "[  ";
		for(Entry<LocalDateTime, String> coisa : user.getCoisasAFazer().entrySet()) {
			String chave = coisa.getKey().toString();
			novasChavesCoisasAFazer += "'"+chave + "', ";
			if(!antigasChavesDeCoisasAFazer.contains(chave)) {
				this.transaction("MATCH (u:User) WHERE id(u)="+user.getId()+
								" CREATE (u)-[:coisa_a_fazer{chave:'"+chave+"'}]->(:coisa_a_fazer{coisa_a_fazer:'"+coisa.getValue()+"'})"); 
			}
		}
		novasChavesCoisasAFazer = novasChavesCoisasAFazer.substring(0, novasChavesCoisasAFazer.length()-2);
		novasChavesCoisasAFazer += "]";
		
		String deleCoisas = "MATCH (u:User)-[e:coisa_a_fazer]->(x) WHERE id(u)="+user.getId()+" AND NOT e.chave IN "+novasChavesCoisasAFazer+" DELETE e";
		this.transaction(deleCoisas);
		
	}

	@Override
	public void delete(Object id) {

		// CUIDADO: O DELETE x (LÁ NO FIM), DELETA O OBJETO RELACIONADO. ISSO É EQUIVALENTE A UMA DELEÇÃO EM CASCATA.
		// PODE SER QUE NÃO SE DESEJE APAGAR O OBJETO RELACIONADO (EM UMA RELAÇÃO N para N, POR EXEMPLO).
		// NESSE CASO, BASTA TIRAR O X LÁ DO FIM, ENTÃO SÓ A RELAÇÃO É APAGADA
		String deleteRelacionamentos = "MATCH (u:User)-[e]->(x) WHERE id(u)="+id+" DELETE e, x";
		String deleteUser = "MATCH (u:User) WHERE id(u)="+id+" DELETE u";
		this.transaction(deleteRelacionamentos);
		this.transaction(deleteUser);
	}

	@Override
	public User find(Object id) {
		
		String operationFindUser = "MATCH (n:User) WHERE id(n) = "+id+" RETURN id(n), n";
		StatementResult resultUser = this.transaction(operationFindUser);
		if(!resultUser.hasNext()) return null;
		
		String operationFindRelacionamentos = "MATCH (n:User)-[e]->(m) WHERE id(n)="+id+" RETURN n, e, m";
		StatementResult resultRelacionamentos = this.transaction(operationFindRelacionamentos);
		
		Record userRecord = resultUser.next();
		return this.buildUser(userRecord, resultRelacionamentos);
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		String operationFindUsers = "MATCH (u:User) RETURN id(u), u";
		StatementResult resultUsers = this.transaction(operationFindUsers);
		while(resultUsers.hasNext()) {
			Record userRecord = resultUsers.next();
			int id = userRecord.get(0).asInt();
			String operationFindRelacionamentos = "MATCH (n:User)-[e]->(m) WHERE id(n)="+id+" RETURN n, e, m";
			StatementResult resultRelacionamentos = this.transaction(operationFindRelacionamentos);
			User user = this.buildUser(userRecord, resultRelacionamentos);
			users.add(user);
		}
				
		return users;
	}
	
	private User buildUser(Record record, StatementResult stmtRelacionamentos) {
		
		int id = record.get(0).asInt();
		String nome = record.get(1).asNode().get("nome").asString();
		List<Object> topPlaces = record.get(1).asNode().get("top_places").asList();
		
		User user = new User(id, nome);
		for(Object topPlace : topPlaces) {
			user.addTopPlace(String.valueOf(topPlace));
		}
		
		while(stmtRelacionamentos.hasNext()) {
			Record relacionamentoRecord = stmtRelacionamentos.next();
			Relationship relacionamento = relacionamentoRecord.get(1).asRelationship();
			Node destino = relacionamentoRecord.get(2).asNode();
			
			switch(relacionamento.type()) {
				case "email":
					user.addEmail(destino.get("email").asString());
					break;
				case "coisa_a_fazer":
					LocalDateTime chave = LocalDateTime.parse(relacionamento.get("chave").asString());
					String valor = destino.get("coisa_a_fazer").asString();
					user.addCoisaAFazer(chave, valor);
					break;
			}
		}
		
		return user;
	}
}










