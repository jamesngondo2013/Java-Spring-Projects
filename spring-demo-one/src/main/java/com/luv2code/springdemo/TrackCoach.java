package com.luv2code.springdemo;

public class TrackCoach implements Coach{
	
private FortuneService fortuneService;
	
	public TrackCoach(FortuneService fortuneService) {
		
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		
		return "Run a 5K every morning...";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}
	
	//add a init method - could be db init etc
	public void doMyStartupStuff() {
		System.out.println("Inside doMyStartuoStuff() ...");
	}
	
	// add a init method - could be db init etc
	public void doMyCleanUpStuff() {
		System.out.println("Inside doMyCleanUpStuff() ...");
	}

}
