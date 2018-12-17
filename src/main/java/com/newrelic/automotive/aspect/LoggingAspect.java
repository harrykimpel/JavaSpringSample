	
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggingAspect Class
 * @author Ciber Momentum Team
 */
 
@Aspect
public class LoggingAspect {
	
	Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* com.newrelic.automotive.controller.*.*(..))")
	public void beforeAdvice1 (JoinPoint joinPoint){
		log.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName()+"  -  Started execution of Method :::"
			+ joinPoint.getSignature().getName()
			+ "() for Class ::: "
			+ joinPoint.getSignature().getDeclaringTypeName());
	}
	@After("execution(* com.newrelic.automotive.controller.*.*(..))")
	public void afterAdvice1 (JoinPoint joinPoint){
		
		log.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName()+"  -  Completed execution of Method :::"
			+ joinPoint.getSignature().getName()
			+ "() for Class ::: "
			+ joinPoint.getSignature().getDeclaringTypeName());		
	}
	
	@Before("execution(* com.newrelic.automotive.service.*.*(..))")
	public void beforeAdvice2 (JoinPoint joinPoint){
		log.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName()+"  -  Started execution of Method :::"
			+ joinPoint.getSignature().getName()
			+ "() for Class ::: "
			+ joinPoint.getSignature().getDeclaringTypeName());
	}
	@After("execution(* com.newrelic.automotive.service.*.*(..))")
	public void afterAdvice2 (JoinPoint joinPoint){
		
		log.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName()+"  -  Completed execution of Method :::"
			+ joinPoint.getSignature().getName()
			+ "() for Class ::: "
			+ joinPoint.getSignature().getDeclaringTypeName());		
	}
	
	@Before("execution(* com.newrelic.automotive.dao.*.*(..))")
	public void beforeAdvice3 (JoinPoint joinPoint){
		log.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName()+"  -  Started execution of Method :::"
			+ joinPoint.getSignature().getName()
			+ "() for Class ::: "
			+ joinPoint.getSignature().getDeclaringTypeName());
	}
	@After("execution(* com.newrelic.automotive.dao.*.*(..))")
	public void afterAdvice3 (JoinPoint joinPoint){
		
		log.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName()+"  -  Completed execution of Method :::"
			+ joinPoint.getSignature().getName()
			+ "() for Class ::: "
			+ joinPoint.getSignature().getDeclaringTypeName());		
	}
	
	@Before("execution(* com.newrelic.automotive.entity.*.*(..))")
	public void beforeAdvice4 (JoinPoint joinPoint){
		log.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName()+"  -  Started execution of Method :::"
			+ joinPoint.getSignature().getName()
			+ "() for Class ::: "
			+ joinPoint.getSignature().getDeclaringTypeName());
	}
	@After("execution(* com.newrelic.automotive.entity.*.*(..))")
	public void afterAdvice4 (JoinPoint joinPoint){
		
		log.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName()+"  -  Completed execution of Method :::"
			+ joinPoint.getSignature().getName()
			+ "() for Class ::: "
			+ joinPoint.getSignature().getDeclaringTypeName());		
	}
	

}

