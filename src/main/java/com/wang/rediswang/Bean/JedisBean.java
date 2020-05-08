package com.wang.rediswang.Bean;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
@Component
public class JedisBean extends Jedis{
	
	private String name;
	
	public JedisBean() {
		super("127.0.0.1", 6379);
		this.name="self_jedis_bean";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
