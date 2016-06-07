/**
 * 
 */
package com.jdx.master.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
/**
 * @author EQTIC_PROD_JFH
 *
 */
@Controller
public class MasterController {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(MasterController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	protected String getMessage(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}

	protected String getMessage(String message, String[] args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}
	
}
