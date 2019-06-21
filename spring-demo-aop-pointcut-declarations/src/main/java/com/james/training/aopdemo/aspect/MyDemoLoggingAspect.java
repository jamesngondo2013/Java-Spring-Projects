package com.james.training.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	//declare Pointcut expression
	@Pointcut("execution(* com.james.training.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}	
	
	//re-using forDaoPackage()
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n=====>>> Executing @Before advice on method <<<======");
	}
	
	//re-using forDaoPackage()
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
		System.out.println("\n=====>>> Perform Analytics <<<======");
	}
	
}
