package com.wang.rediswang.common;

import java.util.Date;

//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

public class SchedulerAction {

//	@Scheduled(cron="0/5 * * * * ?")
	public void testSchedluer() {
		System.err.println("******************每隔一段时间执行一次：  "+new Date()+"******************");
	}
	
}
