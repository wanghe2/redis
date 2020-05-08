package com.wang.rediswang.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.rediswang.Bean.JedisFactory;

import redis.clients.jedis.Jedis;

@RestController
public class QueueController {

	@Resource
	private JedisFactory jedisFactory;
	
	/**
	 * 这里模拟一个优先队列
	 * @return
	 */
	@RequestMapping("/queueSend")
	public String queueSend() {
		Jedis redis=jedisFactory.getJedis();
		redis.lpush("cs", "good","bad");
		for(int i=0;i<10;i++) {
			redis.lpush("cs", "perfect","amazing");
		}
		redis.lpush("higher_cs", "yes","no");
		System.err.println("********发了几条消息***********");
		return "优先级队列";
	}
	
	@RequestMapping("/delayQueue")
	public String delayQueue() {
		Jedis redis=jedisFactory.getJedis();
		Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 10);
        redis.zadd("orderId", (instance.getTimeInMillis()) / 1000, "延时队列");
        System.err.println("********发了条延时消息 "+new Date()+"***********");
		return "延时队列";
	}
}
