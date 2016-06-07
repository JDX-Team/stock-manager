package com.jdx.common.service.interfaces;

import java.util.List;

/**
 * Abstract interface for service layer
 *
 * @param <E>
 *            managed entity
 */
public abstract interface GenericService<E> {
	
	/**
	 * 
	 * @return entity list
	 */
	public List<E> list();
	
	/**
	 * 
	 * @return entity list
	 */
	public List<E> list(E entity);
	
	/**
	 * Add entity to repository
	 */
	public E add(E entity);
	
	/**
	 * 
	 * @return entity read
	 */
	public E read(E entity);
	
	/**
	 * 
	 * @return entity update
	 */
	public void update(E entity);
	
	/**
	 * 
	 * @return entity delete
	 */
	public void delete(E entity);

}
