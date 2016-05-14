package com.jdx.admusr.security;

import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdx.admusr.annotation.SecuredController;
import com.jdx.admusr.model.ActionEntity;
import com.jdx.admusr.model.ControllerEntity;
import com.jdx.admusr.model.FunctionalityEntity;
import com.jdx.admusr.service.interfaces.ActionService;
import com.jdx.admusr.service.interfaces.ControllerService;
import com.jdx.admusr.service.interfaces.FunctionalityService;

/**
 * Class who recover list of controllers from spring context and populate security tables
 *
 */
//@Component
public class SecurityInitializer implements ApplicationListener<ContextRefreshedEvent>{
	
    private static final String INITIALIZE_SECURITY_PARAM = "jdx.security.initialization";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInitializer.class);
    
	ApplicationContext applicationContext;
	
	@Autowired
	ControllerService controllerService;
	
	@Autowired
	FunctionalityService funcService;
	
	@Autowired
	ActionService actionService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (!Boolean.TRUE.toString().equals(System.getProperty(INITIALIZE_SECURITY_PARAM)))
		{
			return;
		}
		
		//Obtain all controllers from context
		Map<String, Object> controllers = event.getApplicationContext().getBeansWithAnnotation(SecuredController.class);
		
		//Iterate over all controllers
		for (String controller : controllers.keySet()) {
			
			String controllerName = controllers
					.get(controller)
					.getClass()
					.getAnnotation(SecuredController.class).value() != null ? 
							controllers
							.get(controller)
							.getClass()
							.getAnnotation(SecuredController.class)
							.value() 
							: 
								controller;
			
			//TODO maybe use a new annotation with controller entity
			ControllerEntity controllerEntity = controllerService.find(controllerName);
			
			//If entity doesn't exists create it
			if(controllerEntity==null)
			{
				controllerEntity = new ControllerEntity();
				controllerEntity.setController(controllerName);
				
				controllerEntity = controllerService.add(controllerEntity);
			}
			
			Object controllerObj = controllers.get(controller);
			
			Method methods [] = controllerObj.getClass().getDeclaredMethods();
			
			//Iterate over methods name
			for (Method method : methods) {
				if(method.getAnnotation(RequestMapping.class) != null){
					
					//Find action on database
					ActionEntity actionEntity = actionService.find(method.getName());
					
					//If entity doesn't exists create it
					if(actionEntity==null)
					{
						actionEntity = new ActionEntity();
						actionEntity.setAction(method.getName());
						
						actionEntity = actionService.add(actionEntity);
					}
					
					//If initialization fails we ignore it, the server startup continues as normal
					try{
						//Create the new functionality
						FunctionalityEntity func = new FunctionalityEntity();
						func.setAction(actionEntity);
						func.setController(controllerEntity);
						
						funcService.add(func);
					}catch(Exception e)
					{
						LOGGER.error("Error creating functionality on SecurityInitializer: "+e.getMessage());
					}
					
				}
					
			}
			
		}
		
	}
}
