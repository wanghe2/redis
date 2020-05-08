package com.wang.rediswang.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.rediswang.Bean.JedisFactory;

import redis.clients.jedis.JedisPubSub;
/**
 * 主要测试redis的发布和订阅
 * 
 * sub 订阅和 pub 发布应该是不同的线程，模拟不同的客户端
 * @author wanghe
 *
 */
@RestController
public class PubSubController {
	
	@Resource
	private JedisFactory jedisFactory;
	
	@RequestMapping("/redispub")
	public String redisPub() {
		redisPublish();
		return "发布成功";
	}
	
	@RequestMapping("/redissub")
	public String redisSub() {
		redisSubsribe();
		return "订阅成功";
	}
	
	/**
	 * 订阅
	 */
	public void redisSubsribe() {
		JedisPubSub jedisPubSub = new JedisPubSub() {
			 
			@Override
			public void onMessage(String channel, String message) {
				System.err.println("*******************"+channel);
				System.err.println("*******************"+message);
			}
 
		};
		jedisFactory.getJedis().subscribe(jedisPubSub, "redisChat");
	}
	
	/**
	 * 发布
	 */
	public void redisPublish() {
		String channel="redisChat";
		String message="test publish substribe";
		jedisFactory.getJedis().publish(channel, message);	
	}
}
