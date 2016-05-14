package com.jdx.test.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.jdx.common.config.FrontResourceBundleMessageSource;


/**
 * Clase que configura Spring MVC en la aplicacion
 * <p/>
 * TODO: Hay que anadir para escanear los paquetes que necesite la aplicacion
 **/
@Configuration
@EnableWebMvc
@ComponentScan({ "com.jdx.**.web"})
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
    
    /**
     * Configura el front messages Resource del Front
     * 
     * @return una instancia del gestor de mensajes de front de la aplicacion. 
     */
    @Bean(name = "frontMessageSource")
    public MessageSource configureFrontMessageSource() {
    	FrontResourceBundleMessageSource messageSource = new FrontResourceBundleMessageSource();
        messageSource.setBasename("classpath:/locale/web/messages");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        
        return messageSource;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        FixedLocaleResolver localeResolver = new FixedLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }


}
