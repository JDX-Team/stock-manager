package com.jdx.admusr.repository.interfaces;

import java.util.Set;

import com.jdx.admusr.model.FunctionalityEntity;
import com.jdx.common.repository.interfaces.GenericRepository;

/**
 * Interface of functionality repository
 *
 */
public interface FunctionalityRepository extends GenericRepository<FunctionalityEntity>{

	/**
	 * Returns the set of keys of rights for an user
	 * @param username
	 * @return
	 */
	public Set<String> getRights(String username);
	
}
