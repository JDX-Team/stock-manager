package com.jdx.admusr.repository.interfaces;

import com.jdx.admusr.model.UserEntity;
import com.jdx.common.repository.interfaces.GenericRepository;

public interface UserRepository extends GenericRepository<UserEntity>{
	
	/**
	 * Method to check if an user exists on LDAP
	 * @param username username to find
	 * @return true if user found
	 */
	public Boolean checkNombre(String nombre);
	
	/**
	 * Method to find user by username
	 * @param username username to find
	 * @return entity found
	 */
	public UserEntity find(String username);
}
