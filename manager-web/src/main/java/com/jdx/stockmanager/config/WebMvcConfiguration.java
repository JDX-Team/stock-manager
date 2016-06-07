package com.jdx.stockmanager.config;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;


/**
 * Clase que configura Spring MVC en la aplicacion
 * <p/>
 * TODO: Hay que anadir para escanear los paquetes que necesite la aplicacion
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableWebMvc
@ComponentScan({ "com.jdx.**.web"})
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	
	@Autowired
	MessageSource frontMessageSource;

	/**
     * Configura el {@link TemplateResolver} para que busque las plantillas de
     * de Thymeleaf en el classpath bajo la carpeta "templates".
     * <p/>
     * TODO: si no se usa Thymeleaf se debe quitar este metodo. Incluir su lugar
     * uno que configure un {@link InternalResourceViewResolver} si se utilizan
     * jsps. Ver el metodo comentado al final de la clase.
     * 
     * @return el template resolver de Thymeleaf
     **/
	 @Bean
    public TemplateResolver springTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setOrder(2);
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
	 
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/").setViewName("index");
        registry.addRedirectViewController("/secure", "/");
    }
    


    /**
     * Configura el motor de templates de Thymeleaf.
     * <p/>
     * TODO: si no se usa Thymeleaf se debe quitar este metodo. Incluir su lugar
     * uno que configure un {@link InternalResourceViewResolver} si se utilizan
     * jsps. Ver el metodo comentado al final de la clase.
     * 
     * @return el motor de templates de Thymeleaf
     **/
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(springTemplateResolver());
        
        Set<IDialect> dialects = new HashSet<IDialect>();
        dialects.add(new SpringSecurityDialect());
        
        engine.setAdditionalDialects(dialects);
        engine.setTemplateEngineMessageSource(frontMessageSource);
        
        return engine;
    }

    
    /**
     * Configura el view resolver de Thymeleaf.
     * <p/>
     * TODO: si no se usa Thymeleaf se debe quitar este metodo. Incluir su lugar
     * uno que configure un {@link InternalResourceViewResolver} si se utilizan
     * jsps. Ver el metodo comentado al final de la clase.
     * 
     * @return el view resolver de Thymeleaf
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    /**
     * Configura el gestor de recursos para que mapee las peticiones a las rutas
     * "/static/**" a los ficheros ubicados bajo la carpeta "static" en el
     * classpath.
     * 
     * TODO: quitar este metodo si la aplicacion no va a tener contenido
     * estatico
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.setOrder(Ordered.LOWEST_PRECEDENCE);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:templates/");
    }

    /**
     * Configura el gestor de mapeos
     *
     * @return el RequestMappingHandlerMapping configurado para hacer
     *         correspondencia exacta
     */
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
        configurer.setUseTrailingSlashMatch(false);
    }
    
    /**
     * Configura el idioma por defecto del aplicativo
     *
     * @return el LocaleResolver configurado 
     */
    @Bean
    public LocaleResolver localeResolver() {
        FixedLocaleResolver localeResolver = new FixedLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

}
