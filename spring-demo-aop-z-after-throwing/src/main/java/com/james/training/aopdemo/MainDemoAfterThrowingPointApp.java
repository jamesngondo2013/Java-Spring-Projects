package com.james.training.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.james.training.aopdemo.dao.AccountDAO;
import com.james.training.aopdemo.dao.MembershipDAO;

public class MainDemoAfterThrowingPointApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the account bean from spring continer
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

		List<Account> theAccounts = null;

		try {
			//add boolean flag to simulate exceptions
			boolean tripWire = true;
			
			// call method to find the accounts
			theAccountDAO.findAccounts(tripWire);
			
		} catch (Exception exc) {
			System.out.println("\n\nMain Program:caught exception : "+ exc);
		}

		// display the accounts
		System.out.println("\n\nMain Program: MainDemoAfterThrowingPointApp");
		System.out.println("----------");

		System.out.println(theAccounts);

		System.out.println("\n");

		// close the context
		context.close();
	}

}
