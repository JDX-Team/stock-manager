package com.jdx.test.common.web;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.jdx.common.errors.web.GlobalDefaultExceptionHandler;
import com.jdx.common.web.GenericController;

public abstract class ControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	protected MockMvc mockMvc;
	
	/**
	 * SetUp method, initialize mockMvc with available controllers
	 */
	public void setUp(GenericController<?> controller){
		
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate4Module());
        //Configuration for ignore unknown properties
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        //Configuration exception handlers
        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler", GlobalDefaultExceptionHandler.class);
        final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);
        messageConverter.setObjectMapper(mapper);
		
		mockMvc = standaloneSetup(controller)
				//Message converter to support hibernate entity serialization
				.setMessageConverters(messageConverter) 
				//Handler exception
				.setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
	            .build();
		
	}

}
