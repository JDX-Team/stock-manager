package com.jdx.stockmanager.config;


import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.jdx.common.config.JdxWebConfiguration;
import com.jdx.common.config.basic.RootContextConfiguration;
import com.jdx.config.AdmUsrServiceConfig;
import com.jdx.config.AdmUsrWebConfiguration;
import com.jdx.config.WebSecurityConfig;
import com.jdx.stockmanager.backend.config.JpaDatabaseConfiguration;

/**
 * Clase de inicializacion de la aplicacion web
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

    /**
     * Contructor de una instancia de inicializacion web, 
     * incluimos el logger de arranque
     */
    public WebAppInitializer() {
        LOGGER.info("Starting.... {}", this.getClass().getName());
    }

    /**
     * Devuelve la lista de componentes de aplicacion que deben cargarse en el
     * contexto Root de spring.
     * 
     * @return la lista de componentes de aplicacion que deben cargarse en el
     *         contexto Root de spring.
     */
    protected Class<?>[] preRootContextClassConfiguration() {
        return new Class[] {
        		JpaDatabaseConfiguration.class,
        		AdmUsrServiceConfig.class,
        		WebSecurityConfig.class
        		
        	};
    }

    /**
     * Devuelve la lista de componentes de aplicacion que deben cargarse en el
     * contexto Web de spring.
     * 
     * @return la lista de componentes de aplicacion que deben cargarse en el
     *         contexto Web de spring.
     */
    protected Class<?>[] preServletConfigClasses() {
        return new Class[] { 
        		JdxWebConfiguration.class,
        		AdmUsrWebConfiguration.class,
        		WebMvcConfiguration.class};
    }

	@Override
	protected Class<?>[] getRootConfigClasses() {
		Class<?>[] root = new Class[] { RootContextConfiguration.class };

        Class<?>[] preRootClass = preRootContextClassConfiguration();

        if (preRootClass != null) {
            Class<?>[] newRoot = new Class<?>[preRootClass.length + root.length];
            System.arraycopy(preRootClass, 0, newRoot, 0, preRootClass.length);
            System.arraycopy(root, 0, newRoot, preRootClass.length, root.length);
            root = newRoot;
        }

        if(logger.isDebugEnabled()){
           logger.debug("Root classes: " + Arrays.toString(root));
        }
        
        return root;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class<?>[] servletClass = new Class[] {};

		Class<?>[] preServletClass = preServletConfigClasses();

		if (preServletClass != null) {
			Class<?>[] newServletClass = new Class<?>[preServletClass.length + servletClass.length];
			System.arraycopy(preServletClass, 0, newServletClass, 0, preServletClass.length);
			System.arraycopy(servletClass, 0, newServletClass, preServletClass.length, servletClass.length);
			servletClass = newServletClass;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Servlet classes: " + Arrays.toString(servletClass));
		}

		return servletClass;
	}



    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
//    
//    @Override
//    protected Filter[] getServletFilters() {
//
//        return new Filter[]{
//            new DelegatingFilterProxy("springSecurityFilterChain")
//        };
//    }
  
}
