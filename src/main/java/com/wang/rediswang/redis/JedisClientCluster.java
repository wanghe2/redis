package com.wang.rediswang.redis;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
/**
 * jedisCluster专门用于集群模式
 * @author wanghe
 *
 */
public class JedisClientCluster implements JedisClient {
	private JedisCluster jedisCluster;
	
	public JedisClientCluster() {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.25.130", 7001));
        nodes.add(new HostAndPort("192.168.25.130", 7002));
        nodes.add(new HostAndPort("192.168.25.130", 7003));
        nodes.add(new HostAndPort("192.168.25.130", 7004));
        nodes.add(new HostAndPort("192.168.25.130", 7005));
        nodes.add(new HostAndPort("192.168.25.130", 7006));
        this.jedisCluster = new JedisCluster(nodes);
  	}
	
	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}
	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}
	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}
	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}
	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}
	@Override
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}
	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}
	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}
	@Override
	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}
	@Override
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}
	@Override
	public Long hdel(String key, String... field) {
		return jedisCluster.hdel(key, field);
	}
	@Override
	public Boolean hexists(String key, String field) {
		return jedisCluster.hexists(key, field);
	}
	@Override
	public List<String> hvals(String key) {
		return jedisCluster.hvals(key);
	}
	@Override
	public Long del(String key) {
		return jedisCluster.del(key);
	}
}
