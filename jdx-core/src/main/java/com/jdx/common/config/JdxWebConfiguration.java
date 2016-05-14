package com.jdx.common.config;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;


/**
 * Clase que configura Spring MVC en la aplicacion
 * <p/>
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableWebMvc
@ComponentScan({ "com.jdx.**.web"})
public class JdxWebConfiguration extends WebMvcConfigurerAdapter {

    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate4Module());
        //Configuration for ignore unknown properties
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        messageConverter.setObjectMapper(mapper);
        return messageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }
  
    /**
     * Configura el front messages Resource del Front
     * 
     * @return una instancia del gestor de mensajes de front de la aplicacion. 
     */
    @Bean(name = "frontMessageSource")
    public MessageSource configureFrontMessageSource() {
    	FrontResourceBundleMessageSource messageSource = new FrontResourceBundleMessageSource();
        messageSource.setBasename("classpath*:/locale/web/messages");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        
        return messageSource;
    }
}
