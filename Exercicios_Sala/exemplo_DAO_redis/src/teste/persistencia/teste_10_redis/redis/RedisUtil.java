package teste.persistencia.teste_10_redis.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	
	private static final String SERVER_IP = "localhost";
	private static final JedisPool pool = new JedisPool(new JedisPoolConfig(), SERVER_IP);

	public static Jedis getClient() {
		return pool.getResource();
	}
	
	public static void closeClient(Jedis client) {
		client.close();
	}
}
