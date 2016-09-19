package com.jdx.master.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jdx.common.repository.DefaultGenericRepository;
import com.jdx.master.repository.interfaces.MasterRepository;


@Repository
public class JpaMasterRepository<E> extends DefaultGenericRepository<E> implements MasterRepository<E>{
	
	@PersistenceContext
	protected EntityManager em;
	/**
	 * List Method. Obtains the list for a specific entity
	 * 
	 * @param entity
	 * @return List<E>
	 */
	public List<E> list(){
		return null;
	}
	/**
	 * Read Method. Obtains the detail for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public E read(E entity){
		return null;
	}

	/**
	 * Add Method. Validate insert for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public E add(E entity){
		return null;
	}

	/**
	 * Update Method. Validate update for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public void update(E entity){

	}

	/**
	 * Delete Method. Validate delete for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public void delete(E entity){

	}	
}
