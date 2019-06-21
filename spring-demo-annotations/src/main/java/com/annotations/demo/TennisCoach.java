package com.annotations.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class TennisCoach implements Coach {

@Autowired
@Qualifier("randomFortuneService")
private FortuneService fortuneService;

	public TennisCoach() {
		System.out.println(">>Inside Tennis default constructor...");
	}
	
	/*
	 * @Autowired 
	  public TennisCoach(FortuneService fortuneService) {
	  
	  this.fortuneService = fortuneService; }
	 */

	@Override
	public String getDailyWorkout() {
		return "Practice your backend volley...";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
	/*
	 * @Autowired 
	  public void setFortuneService(FortuneService fortuneService) {
	  System.out.println(">>Tennis Coach...inside setFortuneService()");
	  this.fortuneService = fortuneService; }
	 */
	

	//add a init method - could be db init etc
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println("Inside doMyStartuoStuff(DB Initilization) ...");
	}
	
	// add a init method - could be db init etc
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println("Inside doMyCleanUpStuff() ...");
	}
	
}
