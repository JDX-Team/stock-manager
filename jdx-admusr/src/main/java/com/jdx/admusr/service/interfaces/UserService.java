package com.jdx.admusr.service.interfaces;

import com.jdx.admusr.model.UserEntity;
import com.jdx.common.service.interfaces.GenericService;

public interface UserService extends GenericService<UserEntity> {
	
	/**
	 * Method to find user by username
	 * @param username username to find
	 * @return entity found
	 */
	public UserEntity find(String username);
}
