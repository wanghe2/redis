package com.wang.rediswang.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.rediswang.Bean.JedisBean;

import redis.clients.jedis.Transaction;

/**
 * 测试redis的事务及过期时间
 * @author wanghe
 *
 */
@RestController
public class MultiController {

	@Resource
	private JedisBean jedisBean;
	
	@RequestMapping("/withoutMulti")
	public String withoutMulti() {
		jedisBean.set("name1", "wanghe");
		jedisBean.lpush("studentList", "wanghe","wangjin","caobingzhuang");
		//这是一个错误，测试错误之后下面的操作还能不能被执行
		jedisBean.get("studentList");
		jedisBean.set("name2", "zuojiabao");
		System.err.println("*******"+jedisBean.get("name2")+"*********");
		return "没有事务的操作";
	}
	
	@RequestMapping("/multiRedis")
	private String multiRedis() {
		//开始事务，在执行exec之前都属于事务范围内
        Transaction tx=jedisBean.multi();

    	tx.set("name1", "wanghe");
        tx.lpush("studentList", "wanghe","wangjin","caobingzhuang");
        //这是一个错误，测试错误之后下面的操作还能不能被执行
        tx.get("studentList");
        tx.set("name2", "zuojiabao");
        System.out.println("提交事务");
        //jedis内部方法异常，提交事务执行成功的结果会存入redis数据库，执行失败的不执行
        List<Object> list=tx.exec();
        //每条语句执行结果存入list中
        for (int i = 0; i < list.size(); i++ )
        {
        	System.out.println("list:"+list.get(i));
        }
        System.err.println("*****"+jedisBean.get("name2")+"*********");
		return "带有事务的操作";
	}
	
}
