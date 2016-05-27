package com.jdx.test.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Esta clase se carga antes del contexto de aplicacion de Spring.
 * 
 * Para poder cargar esta clase es necesario realizar
 * 
 * coontainer.setInitParameter("contextInitializerClasses",...
 * 
 * Clase que carga las propiedades y las mete en el Environment de Spring, 
 * además carga logback con las propiedades definidas
 * Esta carga se condiciona si la pieza de carga de propiedades está activada
 * 
 */
public class BootStrapInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		
    }
}
