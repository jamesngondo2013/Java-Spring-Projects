package com.james.training.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.james.training.aopdemo.dao.AccountDAO;
import com.james.training.aopdemo.dao.MembershipDAO;

public class MainDemoJoinPointApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the account bean from spring continer
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		Account myAccount = new Account();
		myAccount.setName("James");
		myAccount.setLevel("Gold");
		
		//call the account business method
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		theAccountDAO.setName("Gilby");
		theAccountDAO.setServices("Silver");
		
		/*
		 * //call the accountdao getter/setter methods theAccountDAO.setName("James");
		 * theAccountDAO.getName();
		 * 
		 * theAccountDAO.setServices("DEV 6"); theAccountDAO.getServices();
		 */
		//
		
		//get the membership bean from spring continer
		MembershipDAO theMemberDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		
		
		//call the mebership business method
		theMemberDAO.addSillyMember();
		
		theMemberDAO.goToSleep();
		//close the context
		context.close();
	}

}
