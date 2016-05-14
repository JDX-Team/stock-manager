package com.jdx.admusr.service.interfaces;

import java.util.List;
import java.util.Set;

import com.jdx.admusr.model.FunctionalityEntity;
import com.jdx.common.service.interfaces.GenericService;

public interface FunctionalityService extends GenericService<FunctionalityEntity> {

	/**
	 * Returns the functionalities asociates  to one controller
	 * @param controller
	 * @return
	 */
	public List<FunctionalityEntity> list(FunctionalityEntity entity);
	
	/**
	 * Returns the set of keys of rights for an user
	 * @param username
	 * @return
	 */
	public Set<String> getRights(String username);
}
