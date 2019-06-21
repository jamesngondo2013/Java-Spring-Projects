package com.james.training.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.james.training.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	// add new advice for @AfterThrowing on the findAccounts method
	@AfterThrowing(pointcut = "execution(* com.james.training.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "exc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable exc) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterThrowing advice on method: " + method);

		// print the result of the method call
		System.out.println("\n=====>>> Exception Result @AfterThrowing: " + exc.getMessage().toString());

	}

	// add new advice for@AfterReturning on the findAccounts method
	@AfterReturning(pointcut = "execution(* com.james.training.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterReturning advice on method: " + method);

		// post-process the data...modify it
		// convert account names to uppercase
		convertAccountNamesToUpperCase(result);

		// print the result of the method call
		System.out.println("\n=====>>> Result: " + result);

	}

	private void convertAccountNamesToUpperCase(List<Account> result) {

		for (Account account : result) {

			String upperName = account.getName().toUpperCase();
			account.setName(upperName);

		}

	}

	// re-using forDaoPackage()
	@Before("com.james.training.aopdemo.aspect.AOPExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {

		System.out.println("\n=====>>> Executing @Before advice on method <<<======");

		// dispaly method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method: " + methodSig);

		// display method arguments

		// get args
		Object[] args = joinPoint.getArgs();

		for (Object tempArgs : args) {
			System.out.println(tempArgs);

			if (tempArgs instanceof Account) {
				// downcast and print account specific stuff
				Account acc = (Account) tempArgs;

				System.out.println("Account Name: " + acc.getName());
				System.out.println("Account Level: " + acc.getLevel());
			}

		}
	}

}
