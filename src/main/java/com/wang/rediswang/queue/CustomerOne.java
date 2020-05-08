package com.wang.rediswang.queue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wang.rediswang.Bean.JedisFactory;

import redis.clients.jedis.Jedis;
@Component
public class CustomerOne extends Thread{
	
	private final static String QUEUE_NAME="cs";
	private final static String HIGHER_QUEUE_NAME="higher_cs";

	private Jedis jedis;
	
	@Autowired
	public CustomerOne(JedisFactory jedisFactory) {
		System.err.println("*******消费者初始化********");
		this.jedis=jedisFactory.getJedis();
	}
	
	@Override
    public void run() {
		while(true) {
			/**
			 * 带有优先级的队列
			 */
			List<String> message= jedis.brpop(0,HIGHER_QUEUE_NAME,QUEUE_NAME);
			if(message.size()>1) {
				System.err.println("消费者1--key :"+message.get(0)+" value :"+message.get(1));
			}
			try {
				/**
				 * 模拟消费队列时耗费一定时间
				 */
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 
	
}
