package com.jdx.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdx.common.repository.interfaces.GenericRepository;
import com.jdx.common.service.interfaces.GenericService;

/**
 * Abstract class with generic behaviors for service layer
 *
 * @param <E> managed entity
 */
// Required transactionality, if any method is invoked a transaction 
// is initialized in case that none transaction exists
@Transactional(propagation=Propagation.REQUIRED)
public abstract class DefaultGenericService<E> implements GenericService<E>{
	
	@Autowired
	GenericRepository<E> repository;
	
	@Autowired
	@Qualifier("messageSource")
    private MessageSource messageSource;
	
	@Override
	public List<E> list() {
				
		return repository.list();
	}
	
	@Override
	public List<E> list(E entity) {
				
		return repository.list(entity);
	}

	@Override
	public E read(int id) {
		return repository.read(id);
		
	}

	@Override
	public void update(E entity) {
		repository.update(entity);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
		
	}

	@Override
	public E add(E entity) {
		return repository.add(entity);
	}
	



	
	protected String getMessage(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}
	 
	protected String getMessage(String message, String [] args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}

	/**
	 * @return the repository
	 */
	public GenericRepository<E> getRepository() {
		return repository;
	}

}