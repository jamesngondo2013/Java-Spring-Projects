package com.example.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.luv2code.springdemo.Coach;

public class BeanLifCycleDemoApp {

	public static void main(String[] args) {
		// load the spring cofig file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
	//	System.out.println(theCoach.getDailyFortune());
		
		context.close();
		
		

	}

}
