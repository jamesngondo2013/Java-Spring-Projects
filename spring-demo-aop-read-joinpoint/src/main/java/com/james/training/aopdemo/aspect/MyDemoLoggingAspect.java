package com.james.training.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.james.training.aopdemo.Account;
import com.james.training.aopdemo.dao.AccountDAO;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	// re-using forDaoPackage()
	@Before("com.james.training.aopdemo.aspect.AOPExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {

		System.out.println("\n=====>>> Executing @Before advice on method <<<======");
		
		//dispaly method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method: " + methodSig);
		
		//display method arguments
		
		
		//get args
		Object[] args = joinPoint.getArgs();
		
		for (Object tempArgs : args) {
			System.out.println(tempArgs);
			
			if (tempArgs instanceof Account) {
				//downcast and print account specific stuff
				Account acc = (Account) tempArgs;
				
				System.out.println("Account Name: " + acc.getName());
				System.out.println("Account Level: " + acc.getLevel());
			}
			
		}
	}
	
}
