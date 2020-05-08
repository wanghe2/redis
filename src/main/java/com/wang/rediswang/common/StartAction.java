package com.wang.rediswang.common;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wang.rediswang.queue.CustomerOne;
import com.wang.rediswang.queue.CustomerTwo;

@Component
public class StartAction implements InitializingBean{
	
	@Autowired
	CustomerOne customerOne;
	
	@Autowired
	CustomerTwo customerTwo;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.err.println("*******开启线程********");
		customerOne.start();
		customerTwo.start();
	}

}
