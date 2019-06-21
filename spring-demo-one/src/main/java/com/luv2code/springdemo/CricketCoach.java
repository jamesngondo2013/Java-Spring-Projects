package com.luv2code.springdemo;

public class CricketCoach implements Coach {
	
	private FortuneService fortuneServices;
	private String emailAddress;
	private String team;
	
	public CricketCoach() {
		System.out.println("Cricket Coach: Inside no-arg constructor");
	}
	//setter method to be called by spring to inject dependency
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("Cricket Coach: Inside setFortuneService()");
		this.fortuneServices = fortuneService;
	}
	
	public void setEmailAddress(String emailAddress) {
		System.out.println("Cricket Coach: Inside setEmailAddress()");
		this.emailAddress = emailAddress;
	}
	
	public void setTeam(String team) {
		System.out.println("Cricket Coach: Inside setTeam()");
		this.team = team;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getTeam() {
		return team;
	}
	
	@Override
	public String getDailyWorkout() {
		
		return "Practice fast bowling for 15 mins...";
	}

	@Override
	public String getDailyFortune() {
		
		return fortuneServices.getFortune();
	}

}
