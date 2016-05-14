package com.jdx.common.errors.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdx.common.exception.RepoFunctionalException;
import com.jdx.common.exception.ServiceFunctionalException;
import com.jdx.common.utils.MessageConstants;

/**
 * Clase que maneja las expceciones que no han sido controladas y llegan a la
 * capa de controller
 * 
 * Devuelve una respuesta de error a la presentación para que se la presente al
 * usuario
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass().getGenericSuperclass().getClass());


	@Autowired
	private MessageSource messageSource;

	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler({ Throwable.class })
	@ResponseBody
	public ErrorResponse errorTechnicalHandler(HttpServletRequest req, HttpServletResponse resp, Throwable exception) {

		ErrorResponse response = new ErrorResponse();

		response.setErrorMsg(getMessage(MessageConstants.ERROR_TECHNICAL));
		logger.error("Error técnico: " + exception.getMessage(), exception);
		
		//500 Internal Server Error
		
		resp.setStatus(500);
		
		return response;
	}

	@ExceptionHandler({ ServiceFunctionalException.class, RepoFunctionalException.class, ObjectOptimisticLockingFailureException.class, SecurityException.class })
	@ResponseBody
	public ErrorResponse errorFunctionalHandler(HttpServletRequest req, HttpServletResponse resp, Exception exception) {

		ErrorResponse response = new ErrorResponse();

		response.setErrorMsg(exception.getMessage());
		
		if(exception instanceof ObjectOptimisticLockingFailureException)
		{
			response.setErrorMsg(getMessage(MessageConstants.CONCURRENCY_ERROR));
		}
		
		if(exception instanceof SecurityException)
		{
			response.setErrorMsg(getMessage(MessageConstants.SECURITY_EXCEPTION));
		}
		
		//422 Unprocessable Entity (WebDAV; RFC 4918)
		resp.setStatus(422);

		return response;
	}

	protected String getMessage(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}

	protected String getMessage(String message, String[] args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}

}