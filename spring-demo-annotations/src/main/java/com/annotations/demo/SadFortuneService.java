package com.annotations.demo;

import org.springframework.stereotype.Component;

@Component
public class SadFortuneService implements FortuneService{

	@Override
	public String getFortune() {
		
		return "Today is not your lucky day...!!!";
	}

}
