package com.jdx.common.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdx.common.errors.web.ErrorResponse;
import com.jdx.common.utils.MessageConstants;

/**
 * Manage exceptions on repository, service and filter layers encapsulating them
 *
 */
@Component
@Aspect
@Configuration	//Añadida instancia de auto configuración
@EnableAspectJAutoProxy
public class ExceptionAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);
	
	@Autowired
    private MessageSource messageSource;
	
	/**
	 * Catch exceptions thrown on repository layer and encapsulate them on
	 * RepoFunctionalException or repoTechnicalException 
	 * 
	 * @param ex exception thrown
	 */
	@AfterThrowing(pointcut = "execution(* com.jdx..*.repository..* (..))", throwing = "ex")
	public void repositoryException(Throwable ex) {

		if(ex instanceof SQLIntegrityConstraintViolationException) {
			throw new RepoFunctionalException(getMessage(MessageConstants.VALIDATION_SAMEID_OVERLAPPING_ERROR),ex);
		}else if(!(ex instanceof RepoFunctionalException)) {
			throw new RepoTechnicalException(ex.getMessage(),ex);
		}
	}
	
	
	/**
	 * Catch exceptions thrown on service layer and encapsulate them on
	 * ServiceFunctionalException or ServiceTechnicalException 
	 * 
	 * @param ex exception thrown
	 */
	
	@AfterThrowing(pointcut = "execution(* com.jdx..*.service..* (..))", throwing = "ex")
	public void serviceException(Throwable ex) {
	    
		if(ex instanceof RepoFunctionalException) {
			
			throw new ServiceFunctionalException(ex.getMessage(),ex);
			
		}else if(!(ex instanceof ServiceFunctionalException)) {
		
			throw new ServiceTechnicalException(ex.getMessage(),ex);
		}
		
	}
	
	/**
	 * Catch exceptions thrown on filter layer returning an error message as response
	 * 
	 * @param joinPoint joinPoint
	 * 
	 * @throws Throwable 
	 */
	
	@Around("execution(* com.common.filter..* (..))")
	public void filterException(ProceedingJoinPoint joinPoint) throws Throwable {
	    
		HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1];
		
		try {
			
			joinPoint.proceed();
			
		} catch(FilterException e) {
			LOGGER.error("Informando de error en filtro: "+e.getMessage());
			
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMsg(e.getMessage());
			
			ObjectMapper mapper = new ObjectMapper();
			
			response.setStatus(e.getErrorCode());
			response.setContentType("application/json");
			response.getWriter().write(mapper.writeValueAsString(errorResponse));
			
		}
		
	}
	
	protected String getMessage(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}
	 
	protected String getMessage(String message, String [] args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}
}
