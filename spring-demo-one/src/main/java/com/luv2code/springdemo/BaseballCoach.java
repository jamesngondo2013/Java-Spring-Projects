package com.luv2code.springdemo;

public class BaseballCoach implements Coach{

private FortuneService fortuneService;
	
	public BaseballCoach(FortuneService fortuneService) {
		
		this.fortuneService = fortuneService;
	}
	
	public String getDailyWorkout() {
		return "Spend 30 mins on batting practice...";
		
	}

	@Override
	public String getDailyFortune() {
		// use my fortuneServive to get a fortune
		return fortuneService.getFortune();
	}
}
