package com.jdx.admusr.filter;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jdx.admusr.annotation.SecuredController;
import com.jdx.admusr.security.CustomUserDetails;

public class UserPermissionInterceptor extends HandlerInterceptorAdapter{
	
	

	//before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
	    throws Exception {
		//Only handle HandlerMethod
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		CustomUserDetails user = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String clazz =  ((HandlerMethod)handler).getBean().getClass().getSimpleName();
		SecuredController securedController =  ((HandlerMethod)handler).getBean().getClass().getAnnotation(SecuredController.class);
		
		//If SecuredController annotation isn't present, allow the execution
		if(securedController == null){
			return true;
		}
		
		//Obtain id from value of securedController if is defined, else user controller name as id
		String controller =securedController.value() != null ? securedController.value() : ((HandlerMethod)handler).getBean().getClass().getSimpleName();
			
		String method = ((HandlerMethod)handler).getMethod().getName();
		
		if(user.getRights().contains(clazz+"."+method))
		{
			return true;
		}
		
		throw new SecurityException("User: '" + user.getUsername()
		          + "' does not have permissions for method " + controller+"."+method);
	}
}
