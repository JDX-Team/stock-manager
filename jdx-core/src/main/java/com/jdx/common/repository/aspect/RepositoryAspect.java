package com.jdx.common.repository.aspect;

import java.lang.reflect.ParameterizedType;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.jdx.common.annotation.DefaultFecthProfile;
import com.jdx.common.annotation.EnableFecthProfile;
import com.jdx.common.repository.DefaultGenericRepository;

/**
 * Aspect that manages the execution of all repository methods
 *
 */
@Component
@Aspect
@Configuration	//Añadida instancia de auto configuración
@EnableAspectJAutoProxy
public class RepositoryAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryAspect.class);
	
	/**
	 * This aspect enables default fetch profile if it exists
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around("execution(* com.jdx..*.repository..* (..))")
	public Object filterException(ProceedingJoinPoint joinPoint) throws Throwable {

		Session session = null;
		
		Class entity=(Class)((ParameterizedType)joinPoint.getTarget().getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		DefaultFecthProfile defaultFetchProfile = (DefaultFecthProfile)entity.getDeclaredAnnotation(DefaultFecthProfile.class);
		EnableFecthProfile fetchProfile = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(EnableFecthProfile.class);
		
		String fetchProfileToEnable= null;
		
		
		if(fetchProfile!= null){
			//Enabling fetch profile if exist
			fetchProfileToEnable=fetchProfile.value();
		} else if(defaultFetchProfile != null){
			//Enabling default fetch profile if is defined
			fetchProfileToEnable=defaultFetchProfile.value();
		}
		
		//enable fetch profile if is defined
		if(fetchProfileToEnable!= null)
		{
			LOGGER.debug("Enabling default fetch profile: "+fetchProfileToEnable);
			session = ((DefaultGenericRepository)joinPoint.getTarget()).getEntityManager().unwrap(Session.class);
			session.enableFetchProfile(fetchProfileToEnable);
		}
		
		//proceed with method execution
		Object result = joinPoint.proceed();
		
		//disable fetch profile
		if(fetchProfileToEnable!=null){
			LOGGER.debug("Disabling default fetch profile: "+fetchProfileToEnable);
			session.disableFetchProfile(fetchProfileToEnable);
		}
		
		return result;
	
	}

}
