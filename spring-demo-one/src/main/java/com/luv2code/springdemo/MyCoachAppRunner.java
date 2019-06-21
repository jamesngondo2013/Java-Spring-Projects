package com.luv2code.springdemo;

public class MyCoachAppRunner {

	public static void main(String[] args) {

		FortuneService happyFortuneService = new HappyFortuneService();
		FortuneService sadFortuneService = new SadFortuneService();
		
		Coach coach = new BaseballCoach(sadFortuneService);
		System.out.println(coach.getDailyWorkout());
		System.out.println(coach.getDailyFortune());
		
	}

}
