package com.jdx.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jdx.common.service.DefaultGenericService;
import com.jdx.master.repository.interfaces.MasterRepository;
import com.jdx.master.service.interfaces.MasterService;

// Required transactionality, if any method is invoked a transaction 
// is initialized in case that none transaction exists

public class DefaultMasterService<E> extends DefaultGenericService<E> implements MasterService<E> {

	@Autowired
	MasterRepository<E> repository;

	/**
	 * List Method. Obtains the list for a specific entity
	 * 
	 * @param entity
	 * @return List<E>
	 */
	public List<E> list() { // PENDIENTE DE ADAPTACIÓN PARAMETROS A CLASE DE
							// GESTION DE FILTROS
		return repository.list();
	}

	/**
	 * Read Method. Obtains the detail for a specific identifier entity
	 * 
	 * @param Entity
	 * @return E
	 */
	public E read(E entity) {
		return repository.read(entity);

	}

	/**
	 * Add Method. Validate insert for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public E add(E entity) {
		return repository.add(entity);
	}

	/**
	 * Update Method. Validate update for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public void update(E entity) {
		repository.update(entity);
	}

	/**
	 * Delete Method. Validate delete for a specific identifier entity
	 * 
	 * @param entity
	 * @return E
	 */
	public void delete(E entity) {
		repository.delete(entity);
	}

}