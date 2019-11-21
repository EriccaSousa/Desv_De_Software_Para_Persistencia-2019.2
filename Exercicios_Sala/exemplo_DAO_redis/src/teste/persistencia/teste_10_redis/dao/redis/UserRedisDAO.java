package teste.persistencia.teste_10_redis.dao.redis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import teste.persistencia.teste_10_redis.dao.UserDAO;
import teste.persistencia.teste_10_redis.model.User;

public class UserRedisDAO extends GenericRedisDAO<User> implements UserDAO {

	public UserRedisDAO() {
		super("users");
	}

	@Override
	public void insert(User user) {
		String userKey = "users:"+user.getId()+":";	
		redisClient.set(userKey+"id", String.valueOf(user.getId()));
		save(user, userKey);
	}

	@Override
	public void update(User user) {
		// deletar coleções para que possam ser inseridas novamente
		String userKey = "users:"+user.getId()+":";
		redisClient.del(userKey+"emails");
		redisClient.del(userKey+"top_places");
		redisClient.del(userKey+"coisas_a_fazer");
		save(user, userKey);
	}

	@Override
	public User find(Object id) {
		String userKey = "users:"+id+":";
		if(redisClient.get(userKey+"id") == null) return null;
		return fromRedis((int)id, userKey);
	}

	@Override
	public List<User> findAll() {
		
		List<User> users = new ArrayList<User>();
		Set<String> keys = redisClient.keys("users:*:id");
		for(String key : keys) {
			int id = Integer.parseInt(redisClient.get(key));
			String userKey = "users:"+id+":";
			users.add(fromRedis(id, userKey));
		}
		return users;
	}
	
	private User fromRedis(int id, String userKey) {
		String nome = redisClient.get(userKey+"nome");
		Set<String> emails = redisClient.smembers(userKey+"emails");
		List<String> topPlaces = redisClient.lrange(userKey+"top_places", 0, -1);
		Map<String, String> aux = redisClient.hgetAll(userKey+"coisas_a_fazer");
		
		User user = new User();
		user.setId(id);
		user.setNome(nome);
		user.setEmails(emails);
		user.setTopPlaces(topPlaces);
		
		for(Entry<String, String> entry : aux.entrySet()) {
			user.addCoisaAFazer(LocalDateTime.parse(entry.getKey()), entry.getValue());
		}
		
		return user;
	}
	

	private void save(User user, String userKey) {
		
		redisClient.set(userKey+"nome", user.getNome());
		
		for(String email : user.getEmails()) {
			redisClient.sadd(userKey+"emails", email);
		}
		
		for(String topPlace : user.getTopPlaces()) {
			redisClient.rpush(userKey+"top_places", topPlace);
		}
		
		for(Entry<LocalDateTime, String> coisa : user.getCoisasAFazer().entrySet()) {
			redisClient.hset(userKey+"coisas_a_fazer", coisa.getKey().toString(), coisa.getValue());
		}
	}
	
}
