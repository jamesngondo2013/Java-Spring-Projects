package com.james.training.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.james.training.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String services;
	
	public String getName() {
		System.out.println(getClass() +" in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() +" in setName()");
		this.name = name;
	}

	public String getServices() {
		System.out.println(getClass() +" in getServices()");
		return services;
	}

	public void setServices(String services) {
		System.out.println(getClass() +" in setServices()");
		this.services = services;
	}

	public void addAccount(Account acc, boolean vip) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT in ACCOUNT DAO");
	}
	
	public void doWork() {
		System.out.println(getClass() + ": DOING WORK: ACCOUNT in ACCOUNT DAO");
	}
}
