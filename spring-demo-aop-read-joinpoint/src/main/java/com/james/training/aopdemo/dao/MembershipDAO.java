package com.james.training.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void addAccount() {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT IN MEMBERSHIP DAO");
	}
	
	public boolean addSillyMember() {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING SILLY MEMBER ACCOUNT IN MEMBERSHIP DAO");
		
		return true;
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": GO TO SLEEP: ADDING IN MEMBERSHIP DAO");
	}
}
