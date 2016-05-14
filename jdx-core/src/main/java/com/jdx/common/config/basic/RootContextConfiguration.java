package com.jdx.common.config.basic;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;



@Order(Ordered.HIGHEST_PRECEDENCE)
@Import(value = {

})
@Configuration(value = "RootContextConfiguration")
public class RootContextConfiguration {
	
	@Bean
	public static PropertyPlaceholderConfigurer applicationProperties() {
	    final PropertyPlaceholderConfigurer applicationProperties = new PropertyPlaceholderConfigurer();
	    applicationProperties.setLocation(new ClassPathResource("application.properties"));
	    return applicationProperties;
	}
	
}
