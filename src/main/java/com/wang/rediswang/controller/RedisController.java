package com.wang.rediswang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.rediswang.Bean.JedisBean;
/**
 * redis的基本操作命令（基于jedis）
 * @author wanghe
 *
 */
@RestController
public class RedisController {
	
	@Resource
	private JedisBean jedisBean;
	
	@RequestMapping("/redis")
	public String redis() {
		testRedis();
		return "hello redis";
	}
	
	public void testRedis() {
		redisZSet();
	}
	
	public void redisStr() {
		jedisBean.get("bean1");
	}
	
	public void redisList() {
		jedisBean.lpush("studentList", "zuojiabao");
		jedisBean.lpush("studentList", "wanghe");
		List<String> list = jedisBean.lrange( "studentList", 0, -1 ); 
		list.size();
	}
	
	public void redisSet() {
		jedisBean.sadd("jijie", "chuntian");
		jedisBean.sadd("jijie", "chuntian");
		jedisBean.sadd("jijie", "xiatian");
		jedisBean.sadd("jijie", "xiatian");
		jedisBean.sadd("jijie", "qiutian");
		Set<String> jijieSet= jedisBean.smembers("jijie");
		jijieSet.size();
	}
	
	public void redisKeys() {
		Set<String>keys= jedisBean.keys("*");
		for (String key : keys) {
			System.out.println("List of stored keys:: " + key);
		}
	}
	
	public void redisHash() {
		String key="user";
		String field_name="name";
		String field_name_value="wanghe";
		String field_city="city";
		String field_city_value="shanghai";
		jedisBean.hset(key, field_name, field_name_value);
		jedisBean.hset(key, field_city, field_city_value);
		
		Map<String, String> map = jedisBean.hgetAll(key);
		Set<Entry<String,String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	
	public void redisZSet() {
		String key = "zset_key";
		Map<String,Double> scoreMembers=new HashMap<String, Double>();
		scoreMembers.put("one", 1.0);
		scoreMembers.put("two", 2.0);
		scoreMembers.put("three", 3.0);
		jedisBean.zadd(key, scoreMembers);
		
		Set<String> zrange = jedisBean.zrange(key, 0, 2);
		
		for (String member : zrange) {
			System.out.println(member);
			System.out.println(jedisBean.zscore(key, member));
		}
	}
}
