package com.jdx.master.config;

import java.util.Set;

import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jdx.master.annotation.MasterTable;
import com.jdx.master.repository.JpaMasterRepository;
import com.jdx.master.service.DefaultMasterService;

/**
 * Generador de Bean de Spring que nos ayudará a generar un clase repository y service
 * genérica autoinstanciable por spring para gestionar todas las tablas maestras
 * de forma común y solo implementar los repository especifícos si son necesarios
 */
@Configuration
@ComponentScan({"com.isb.phoenix.jdx.master.repository", "com.isb.phoenix.jdx.master.service" })
public class CustomBeanCreatorConfig implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry bdr) throws BeansException {

		Reflections reflections = new Reflections("com.isb.phoenix.jdx.master.model");
 		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(MasterTable.class);

		for (Class<?> clazz : annotated) {
			String beanName = clazz.getSimpleName().replace("Entity", "");
			
			ConstructorArgumentValues construcArg = new ConstructorArgumentValues();
			construcArg.addGenericArgumentValue(new ValueHolder(clazz, clazz.getClass().getName()));
			
			//BEAN de la capa repository
			GenericBeanDefinition definitionRepo = new GenericBeanDefinition();
			definitionRepo.setConstructorArgumentValues(construcArg);
			definitionRepo.setBeanClass(JpaMasterRepository.class);
			//definitionRepo.setFactoryMethodName("getService");
			definitionRepo.setAutowireCandidate(true);
//			definitionRepo.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);
			AutowireCandidateQualifier qualifier = new AutowireCandidateQualifier("jpa"+beanName+"MasterRepository");
			definitionRepo.addQualifier(qualifier);
			bdr.registerBeanDefinition("jpa"+beanName+"MasterRepository", definitionRepo);
			
			
			//BEAN de la capa service
			GenericBeanDefinition definitionService = new GenericBeanDefinition();
			definitionService.setConstructorArgumentValues(construcArg);
			definitionService.setBeanClass(DefaultMasterService.class);
//			definitionService.setAttribute("repository1",definitionRepo);
			definitionService.setAutowireCandidate(true);
//			definitionService.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);
			
			MutablePropertyValues values = new MutablePropertyValues();
			values.addPropertyValue("repository", new RuntimeBeanReference("jpa"+beanName+"MasterRepository"));//new RuntimeBeanNameReference("jpa"+beanName+"MasterRepository") 
			definitionService.setPropertyValues(values);
			//definitionService.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
//			definitionService.setLazyInit(false);
//			AutowireCandidateQualifier qualifierService = new AutowireCandidateQualifier("jpa"+beanName+"MasterRepository");
//			definitionService.addQualifier(qualifierService);
		
			bdr.registerBeanDefinition(beanName+"MasterService", definitionService);
			
			
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		
	}

}
