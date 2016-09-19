package com.jdx.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

/**
 * Configuración para cargar los Service Component de Spring.
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ComponentScan({"com.jdx.*.service","com.jdx.*.exception"})
public class AdmUsrServiceConfig {

    /**
     * Configura el messages Resource
     * 
     * @return una instancia del gestor de mensajes de la aplicacion.
     */
    @Bean(name = "messageSource")
    public MessageSource configureMessageSource() {
    	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath*:/locale/messages");
        messageSource.setCacheSeconds(5);
        //messageSource.setCacheSeconds(-1);
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

