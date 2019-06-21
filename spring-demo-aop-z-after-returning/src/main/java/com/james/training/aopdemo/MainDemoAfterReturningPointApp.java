package com.james.training.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.james.training.aopdemo.dao.AccountDAO;
import com.james.training.aopdemo.dao.MembershipDAO;

public class MainDemoAfterReturningPointApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the account bean from spring continer
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		//call method to find the accounts
		List<Account> theAccounts = theAccountDAO.findAccounts();
		
		//display the accounts
		System.out.println("\n\nMain Program: MainDemoAfterReturningPointApp");
		System.out.println("----------");
		
		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		//close the context
		context.close();
	}

}
