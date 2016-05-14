package com.jdx.common.web;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdx.common.config.FrontResourceBundleMessageSource;

/**
 * Controller used to communicate localized messages to front end
 *
 */
@RestController
public class FrontMessagesController {

	@Autowired
	private FrontResourceBundleMessageSource frontMessageSource;
	
	Properties cachedProperties = null;

	/**
	 * Method that reads message properties with current locale and returns all his content
	 * as JSON
	 * 
	 * @return front messages
	 */
	@RequestMapping(value = "/messages", method = { RequestMethod.GET })
	@ResponseBody
	public Properties getMessages() {
		Assert.notNull(frontMessageSource,"No frontMessageSource configured.");
		
		if(cachedProperties == null){
			cachedProperties=frontMessageSource.getMessages(LocaleContextHolder.getLocale());
		}
		
		return cachedProperties;
	}

}
