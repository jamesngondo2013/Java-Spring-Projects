package com.james.training.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {

	// declare Pointcut expression
	@Pointcut("execution(* com.james.training.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {
	}

	// create pointcut for getter
	@Pointcut("execution(* com.james.training.aopdemo.dao.*.get*(..))")
	public void getter() {
	}

	// create pointcut for setter
	@Pointcut("execution(* com.james.training.aopdemo.dao.*.set*(..))")
	private void setter() {
	}

	// create pointcut: include package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {
	}

}
