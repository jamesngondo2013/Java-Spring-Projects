package com.james.training.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.james.training.aopdemo.service.TrafficFortuneService;

public class MainDemoAroundApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the account bean from spring continer
		TrafficFortuneService theTrafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		System.out.println("\nMain Program: MainDemoAroundApp");
		
		System.out.println("calling getFortune");
		
		String data = theTrafficFortuneService.getFortune();
		
		System.out.println("\nMy fortune is: " + data);
		
		System.out.println("Finished");

		// close the context
		context.close();
	}

}
