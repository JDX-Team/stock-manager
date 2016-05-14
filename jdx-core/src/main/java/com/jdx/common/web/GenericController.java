package com.jdx.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import com.jdx.common.model.OkResponse;
import com.jdx.common.utils.MessageConstants;

/**
 * Abstract class with generic behaviors for controller layer
 *
 * @param <E> managed entity
 */
@Controller
public abstract class GenericController<E> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericController.class);
	
	//Path de inicializacion de vista apunta a raiz
	private static final String INIT_VIEW_PATH = "";

	@Autowired
	private MessageSource messageSource;



	protected String getMessage(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}

	protected String getMessage(String message, String[] args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}

	/**
	 * Method for obtain logger from controllers
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Método que genera devuelve el mensaje de operación correcta
	 * @param request
	 * @param response
	 * @return
	 */
	protected Object generateMsg(String code){
		//To be implemented by controllers
		return new OkResponse(getMessage(code));
	}
	
	/**
	 * Método que genera devuelve el mensaje de operación correcta
	 * @param request
	 * @param response
	 * @return
	 */
	protected Object generateMsg(){
		//To be implemented by controllers
		return new OkResponse(getMessage(MessageConstants.OPERATION_SUCCESS));
	}
	
}
