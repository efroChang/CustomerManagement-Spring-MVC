package com.spring.aspect;

import java.util.logging.Logger;
import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// -------------------------------------
	// 1. Set up logger on the console
	// -------------------------------------
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// -------------------------------------
	// 2. Pointcut Declarations
	// -------------------------------------
	// 2.1 All methods under controller package.
	@Pointcut("execution(* com.spring.controller.*.*(..))")
	private void pointcutForController() {
	}

	// 2.2 All methods under service package.
	@Pointcut("execution(* com.spring.service.*.*(..))")
	private void pointcutForService() {
	}

	// 2.3 All methods under dao package.
	@Pointcut("execution(* com.spring.dao.*.*(..))")
	private void pointcutForDAO() {
	}

	// 2.4 Combine all 3 above
	@Pointcut("pointcutForController() || pointcutForService() || pointcutForDAO()")
	private void pointcutForControllerServiceDAO() {
	}

	// -------------------------------------
	// 3. @Before Advice
	// -------------------------------------
	@Before("pointcutForControllerServiceDAO()")
	public void beforeAdvice(JoinPoint joinPoint) {

		// Access Method Name
		String targetMethodName = joinPoint	.getSignature()
											.toShortString();
		myLogger.info("  ====> @Before Target Method Name: " + targetMethodName);

		// Access Parameters
		Object[] params = joinPoint.getArgs();
		for (Object param : params) {
			myLogger.info("  ====> Param: " + param + "; ");
		}
	}

	// -------------------------------------
	// 4. @AfterReturning Advice
	// -------------------------------------
	@AfterReturning(pointcut = ("pointcutForControllerServiceDAO()"), returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		// Access Method Name
		String targetMethodName = joinPoint	.getSignature()
											.toShortString();
		myLogger.info("  <==== @After Target Method Name: " + targetMethodName);

		myLogger.info("  <==== Results: " + result);
	}

}
