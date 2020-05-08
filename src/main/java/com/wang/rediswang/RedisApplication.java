package com.wang.rediswang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class RedisApplication 
{
    public static void main( String[] args )
    {
       SpringApplication.run(RedisApplication.class, args);
    }
    
}
