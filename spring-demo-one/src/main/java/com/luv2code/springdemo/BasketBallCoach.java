package com.luv2code.springdemo;

public class BasketBallCoach implements Coach{

	private FortuneService fortuneService;
	
	public BasketBallCoach(FortuneService fortuneService) {
		
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		
		return "Spend 45 mins on basketball shootout...";
	}

	@Override
	public String getDailyFortune() {
		// use my fortuneServive to get a fortune
		return fortuneService.getFortune();
	}

}
