package com.jdx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jdx.admusr.filter.UserPermissionInterceptor;
import com.jdx.admusr.security.SecurityInitializer;
import com.jdx.common.filter.AjaxTimeoutRedirectFilter;

/**
 * Clase que configura Spring MVC en la aplicacion para la parse admusr
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableWebMvc
@ComponentScan({ "com.jdx.**.web" })
public class AdmUsrWebConfiguration extends WebMvcConfigurerAdapter {

	@Value("${sec.filter.enable}")
	private String secFilterEnable;

	/**
	 * Configure paths of login and logout
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/public/login").setViewName("public/login");
		registry.addRedirectViewController("/public/logout", "/");
	}

	/**
	 * Security initializer, on server startup scan all controllers and register
	 * it on database
	 * 
	 * @return SecurityInitializer bean
	 */
	@Bean
	public SecurityInitializer controllerManager() {
		SecurityInitializer controllerManager = new SecurityInitializer();
		return controllerManager;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addInterceptors(org.springframework.web.servlet.config.annotation.
	 * InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Enable userPermissionInterceptor if sec.filter.enable parameter is
		// true
		if ("true".equals(secFilterEnable)) {
			registry.addInterceptor(userPermissionInterceptor());
		}

	}

	
	/**
	 * Ajax timeout filter
	 * 
	 * @return AjaxTimeoutRedirectFilter bean
	 */
	@Bean
	public AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter() {
		AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter = new AjaxTimeoutRedirectFilter();

		return ajaxTimeoutRedirectFilter;
	}

	/**
	 * Bean UserPermissionInterceptor
	 * 
	 * @return UserPermissionInterceptor
	 */
	@Bean
	public UserPermissionInterceptor userPermissionInterceptor() {
		UserPermissionInterceptor userPermissionInterceptor = new UserPermissionInterceptor();

		return userPermissionInterceptor;

	}

}
