package com.wang.rediswang.queue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wang.rediswang.Bean.JedisFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
@Component
public class CustomerTwo extends Thread{
	
	private Jedis jedis;
	
	@Autowired
	public CustomerTwo(JedisFactory jedisFactory) {
		System.err.println("*******消费者初始化********");
		this.jedis=jedisFactory.getJedis();
	}
	
	@Override
    public void run() {
		while(true) {

            Set<Tuple> order = jedis.zrangeWithScores("orderId", 0, 0);
            if (order == null || order.isEmpty()) {
                try {
                   Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            Tuple tuple = (Tuple) order.toArray()[0];
            double score = tuple.getScore();
            Calendar instance = Calendar.getInstance();
            long nowTime = instance.getTimeInMillis() / 1000;
            if (nowTime >= score) {
                String element = tuple.getElement();
                //zrem会把队列的信息取出并移除
                Long orderId = jedis.zrem("orderId", element);
                if (orderId > 0) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ":redis消费了一个任务：消费的订单OrderId为" + element);
                }
            }
        
		}
	} 
	
}
