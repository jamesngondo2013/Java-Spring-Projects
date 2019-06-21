package com.james.training.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.james.training.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	public List<Account> findAccounts(boolean tripWire){
		
		//simulate exception 
		if (tripWire) {
			throw new RuntimeException("No food for you!!!");
		}
		
		 List<Account> myAccounts = new ArrayList<Account>();
		 
		 //create sample accounts
		 Account temp1 = new Account("James", "Silver");
		 Account temp2 = new Account("Bright", "Platinum");
		 Account temp3 = new Account("Gilby", "Gold");
		 
		 //add accounts to list
		 myAccounts.add(temp1);
		 myAccounts.add(temp2);
		 myAccounts.add(temp3);
		 
		 return myAccounts;
		 
	}
	
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
		return serviceCode;
	}

	public void setServices(String services) {
		System.out.println(getClass() +" in setServices()");
		this.serviceCode = services;
	}

	public void addAccount(Account acc, boolean vip) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT in ACCOUNT DAO");
	}
	
	public void doWork() {
		System.out.println(getClass() + ": DOING WORK: ACCOUNT in ACCOUNT DAO");
	}
}
