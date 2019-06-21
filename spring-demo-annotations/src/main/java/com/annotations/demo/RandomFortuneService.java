package com.annotations.demo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	private String[] data = {
			"Beware of the wolf in sheep's clothing",
			"Diligence is the mother of all good luck",
			"The journey is the reward"
			};
	
	private Random rand = new Random();
	
	@Override
	public String getFortune() {
		int index = rand.nextInt(data.length);
		String theFortune = data[index];
		return theFortune;
	}

}
