/**
 * 
 */
package com.commercetools.stockhandling.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * This class is defining the logging aspects 
 * It is annotated as Aspect due to using of Spring support for AOP
 * Logging Point Cuts defined for all tiers of the project (Rest Controller , Service , DAO)
 * 
 * @author M.Othman
 * */

@Aspect
@Component
public class AppLoggingAspect {

	// adding logging support
	protected static final Logger logger = LogManager.getLogger(AppLoggingAspect.class);

	/**
	 * Define point cut for all methods of Rest Controller with all return types
	 */
	@Pointcut("execution (* com.commercetools.stockhandling.restapi.*.*(..))")
	private void forControllerLog() {
	}

	/**
	 * Define point cut for all methods of Service layer with all return types
	 */
	@Pointcut("execution (* com.commercetools.stockhandling.service.*.*(..))")
	private void forServicesLog() {
	}

	/**
	 * Define point cut for all methods of DAO layer with all return types
	 */
	@Pointcut("execution (* com.commercetools.stockhandling.dao.*.*(..))")
	private void forDaoLog() {
	}

	/**
	 * Merging all point cuts at one Join point
	 * 
	 */
	@Pointcut("forControllerLog() || forServicesLog() || forDaoLog()")
	private void appLog() {
	}

	/**
	 * Logging Aspect will be Executed before each defined point cut. Logging Action
	 * Method with its full qualified name will be logged to Log files.
	 * 
	 * @param jpoint defined for all project layers at appLog and it will be
	 *               supported by Spring
	 */
	@Before("appLog()")
	public void beforeLogging(JoinPoint jpoint) {

		// display method signature
		String methodSig = jpoint.getSignature().toShortString();
		logger.info("=================>  @Action Method: " + methodSig);

		// display method params
		Object[] methodParams = jpoint.getArgs();
		for (Object temp : methodParams) {
			logger.info("=================> arguments: " + temp);

		}

	}

	/**
	 * Logging Aspect will be Executed After Exception Thrown.
	 * It will Log Action Method with its full qualified name will be logged to Log files and Exception details.
	 * 
	 * @param jpoint defined for all project layers at appLog and it will be
	 *               supported by Spring
	 * @param error  Any Exception happen at any defined point cut will be passed by
	 *               spring to this Aspect
	 */
	@AfterThrowing(pointcut = "appLog()", throwing = "error")
	public void afterThrowException(JoinPoint jpoint, Throwable error) {
		String methodSig = jpoint.getSignature().toShortString();
		logger.error("=================> @Exception at Method: " + methodSig);
		logger.error("=================> @Exception Details: " , error);
	}

}

