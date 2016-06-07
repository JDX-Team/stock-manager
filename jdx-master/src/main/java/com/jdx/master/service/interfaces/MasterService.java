package com.jdx.master.service.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdx.common.service.interfaces.GenericService;

/**
 * Abstract class with generic behaviors for service layer
 *
 * @param <E>
 *            managed entity
 */
// Required transactionality, if any method is invoked a transaction
// is initialized in case that none transaction exists
@Transactional(propagation = Propagation.REQUIRED)
public interface MasterService<E> extends GenericService<E> {

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
	 * @param Entity
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