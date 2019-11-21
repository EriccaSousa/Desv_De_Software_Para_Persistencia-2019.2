package teste.persistencia.teste_11_neo4j;

import java.time.LocalDateTime;
import java.util.List;

import teste.persistencia.teste_11_neo4j.dao.UserDAO;
import teste.persistencia.teste_11_neo4j.dao.neo4j.UserNeo4jDAO;
import teste.persistencia.teste_11_neo4j.model.User;

public class Principal {

	public static void main(String[] args) {


		try {

			UserDAO dao = new UserNeo4jDAO();
			// dao01Inserir(dao);
			// dao02findById(dao);
			// dao03Update(dao);
			// dao04DeleteById(dao);
			// dao05DeleteUser(dao);
			dao06FindAll(dao);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("Bye, Neo4j DAO");
	}

	public static void dao06FindAll(UserDAO dao) {
		List<User> users = dao.findAll();
		for(User user : users) {
			System.out.println(user);
		}
	}

	public static void dao05DeleteUser(UserDAO dao) {
		User user = dao.find(62);
		dao.delete(user);
	}

	public static void dao04DeleteById(UserDAO dao) {
		dao.delete(68);
	}

	public static void dao03Update(UserDAO dao) {
		
		
		User u1 = dao.find(6);
		User u2 = dao.find(39);
		User u3 = dao.find(55);
		
		System.out.println(u1);
		System.out.println(u2);
		System.out.println(u3);
		

		u1.setNome("Mario");
		u2.setNome("João");
		u3.setNome("Roberta");

		u1.addTopPlace("Quixeramobim");
		u1.getTopPlaces().remove(0);

		u2.getCoisasAFazer().clear();
		u2.getEmails().clear();
		u2.addEmail("joao@gmail.com");
		u2.addCoisaAFazer(LocalDateTime.of(2000, 1, 1, 0, 0), "fim do mundo");

		u3.getEmails().clear();

		dao.update(u1);
		dao.update(u2);
		dao.update(u3);
		
	}


	public static void dao02findById(UserDAO dao) {	
		int[] ids = {6, 39, 55, 82, 100};

		for(int id : ids) {
			User u = dao.find(id);
			System.out.println(u);
		}
		
	}

	public static void dao01Inserir(UserDAO dao) {

		// inserir o mesmo usuário 4 vezes mudando só o id, só pra testar
		for(int k=1;k<=4;k++) {
			User u = new User(k, "Anderson");
			u.addEmail("a@gmail.com");
			u.addEmail("a@ufc.br");
			u.addEmail("a@ufc.br");
			u.addTopPlace("UFC");
			u.addTopPlace("Quixadá");
			u.addCoisaAFazer(LocalDateTime.now(), "dar aula de persistencia");
			u.addCoisaAFazer(LocalDateTime.of(2020, 1, 7, 23, 55), "dormir");

			dao.insert(u);

			System.out.println(u);
		}

	}
}
