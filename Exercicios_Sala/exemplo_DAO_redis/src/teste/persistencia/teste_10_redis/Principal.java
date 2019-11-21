package teste.persistencia.teste_10_redis;

import java.time.LocalDateTime;
import java.util.List;

import teste.persistencia.teste_10_redis.dao.UserDAO;
import teste.persistencia.teste_10_redis.dao.redis.UserRedisDAO;
import teste.persistencia.teste_10_redis.model.User;

public class Principal {

	public static void main(String[] args) {
		
		UserDAO dao = new UserRedisDAO();
		dao01Inserir(dao);
		// dao02findById(dao);
		// dao03Update(dao);
		// dao04DeleteById(dao);
		// dao05DeleteUser(dao);
		// dao06findAll(dao);
		dao.close();
		
		System.out.println("Bye, Redis DAO");
	}
	
	public static void dao06findAll(UserDAO dao) {
		List<User> users = dao.findAll();
		for(User user : users) {
			System.out.println(user);
		}
	}
	
	public static void dao05DeleteUser(UserDAO dao) {
		User user = dao.find(3);
		dao.delete(user);
	}
	
	public static void dao04DeleteById(UserDAO dao) {
		dao.delete(4);
	}
	
	public static void dao03Update(UserDAO dao) {
		User u1 = dao.find(1);
		User u2 = dao.find(2);
		User u3 = dao.find(3);
		
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
		User u1 = dao.find(1);
		User u4 = dao.find(4);
		
		System.out.println(u1);
		System.out.println(u4);
		
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
		}
		
	}
}
