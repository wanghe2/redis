package com.wang.rediswang.Bean;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@Component
public class JedisFactory {
	Jedis jedis;
	JedisPool pool;
	public JedisFactory() {
		JedisPoolConfig config = new JedisPoolConfig();   
	    config.setMaxIdle(5);   
	    config.setMaxWaitMillis(1000l);   
	    config.setTestOnBorrow(false);   
        // 构造池   
		this.pool = new JedisPool(config,"127.0.0.1",6379, 100000); //容忍的超时时间  
	}
	public Jedis getJedis() {
		return pool.getResource();
	}
	
}
