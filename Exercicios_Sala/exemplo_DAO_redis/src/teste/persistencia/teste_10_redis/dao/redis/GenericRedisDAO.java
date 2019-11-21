package teste.persistencia.teste_10_redis.dao.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import teste.persistencia.teste_10_redis.dao.GenericDAO;
import teste.persistencia.teste_10_redis.model.Bean;
import teste.persistencia.teste_10_redis.redis.RedisUtil;

public abstract class GenericRedisDAO<T extends Bean> implements GenericDAO<T> {

	protected Jedis redisClient;
	private String tableName;
	
	public GenericRedisDAO(String tableName) {
		this.tableName = tableName;
		this.redisClient = RedisUtil.getClient();
	}
	
	@Override
	public void delete(T t) {
		delete(t.getId());
	}
	
	@Override
	public void delete(Object id) {
		Set<String> keys = redisClient.keys(tableName+":"+id+":*");
		for(String key : keys) {
			redisClient.del(key);
		}
	}
	
	@Override
	public void close() {
		RedisUtil.closeClient(this.redisClient);
	}
}
