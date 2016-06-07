package com.jdx.common.repository.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * Abstract interface for repository layer
 *
 * @param <E>
 *            managed entity
 */
public abstract interface GenericRepository<E> {

	/**
	 * Basic method for list all entities
	 * 
	 * @return list of entities
	 */
	public List<E> list();
	
	/**
	 * Basic method for list all entities filtered by provided entity
	 * 
	 * @return list of entities
	 */
	public List<E> list(E entity);
	
	/**
	 * Basic method for read an entity given his entity
	 * @param id entity identifier
	 * @return found entity
	 */
	public E read(E entity);
	
	/**
	 * Basic method for persist given entity
	 * 
	 * @param entity entity to persist
	 */
	public E add(E entity);
	
	/**
	 * Basic method for update given entity
	 * 
	 * @param entity to update
	 */
	public void update(E entity);
	
	/**
	 * Basic method for delete entity
	 * 
	 * @param entity to delete
	 */
	public void delete(E entity);
	
	EntityManager getEntityManager();
	
}
