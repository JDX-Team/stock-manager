package com.jdx.admusr.service;

import org.springframework.stereotype.Service;

import com.jdx.admusr.model.UserEntity;
import com.jdx.admusr.repository.interfaces.UserRepository;
import com.jdx.admusr.service.interfaces.UserService;
import com.jdx.admusr.util.MessageConstants;
import com.jdx.common.exception.RepoFunctionalException;
import com.jdx.common.service.DefaultGenericService;


@Service
public class DefaultUserService extends DefaultGenericService<UserEntity> implements UserService {

	@Override
	public UserEntity add(UserEntity user) {
		
		if (!checkValidUser(user))
			throw new RepoFunctionalException(getMessage(MessageConstants.ERR_USER_EXIST));
		
		return getRepository().add(user);
	}
	
	private Boolean checkValidUser(UserEntity user){
		return ((UserRepository)getRepository()).checkNombre(user.getUser());
	}

	@Override
	public UserEntity find(String username) {
		return ((UserRepository)getRepository()).find(username);
	}
}
