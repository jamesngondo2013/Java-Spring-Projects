package com.james.training.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.james.training.aopdemo.Account;
import com.james.training.aopdemo.dao.AccountDAO;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAdvice {
		
	// re-using forDaoPackage()
	@Before("com.james.training.aopdemo.aspect.AOPExpressions.forDaoPackageNoGetterSetter()")
	public void performApiAnalytics(JoinPoint joinPoint) {
		System.out.println("\n=====>>> @Before Perform API Analytics <<<======");
		
	}

}
