package com.james.training.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.james.training.aopdemo.Account;

@Component
public class AccountDAO {

	public void addAccount(Account acc, boolean vip) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT in ACCOUNT DAO");
	}
	
	public void doWork() {
		System.out.println(getClass() + ": DOING WORK: ACCOUNT in ACCOUNT DAO");
	}
}
