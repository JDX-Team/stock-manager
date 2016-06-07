package com.jdx.master.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jdx.common.repository.interfaces.GenericRepository;

/**
 * This class implements a basics CRUD for Master´s Entitys
 * 
 * @author EQTIC_PROD_JFH
 *
 */
@Repository
public interface MasterRepository<E> extends GenericRepository<E> {
	
	/**
	 * List Method. Obtains the list for a specific entity
	 * 
	 * @param entity
	 * @return List<E>
	 */
	public List<E> list(); // PENDIENTE DE ADAPTACIÓN PARAMETROS A CLASE DE
							// GESTION DE FILTROS

	/**
	 * Read Method. Obtains the detail for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public E read(E entity);

	/**
	 * Add Method. Validate insert for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public E add(E entity);

	/**
	 * Update Method. Validate update for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public void update(E entity);

	/**
	 * Delete Method. Validate delete for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public void delete(E entity);

}
