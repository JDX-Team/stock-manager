package com.jdx.admusr.service;

import org.springframework.stereotype.Service;

import com.jdx.admusr.model.RoleEntity;
import com.jdx.admusr.repository.interfaces.RoleRepository;
import com.jdx.admusr.service.interfaces.RoleService;
import com.jdx.admusr.util.MessageConstants;
import com.jdx.common.exception.RepoFunctionalException;
import com.jdx.common.service.DefaultGenericService;


@Service
public class DefaultRoleService extends DefaultGenericService<RoleEntity> implements RoleService {
	
	@Override
	public RoleEntity add(RoleEntity role) {
		
		if (!checkValidRole(role))
			throw new RepoFunctionalException(getMessage(MessageConstants.ERR_ROLE_EXIST));
		
		return getRepository().add(role);
	}
	
	private Boolean checkValidRole(RoleEntity role){
		return ((RoleRepository)getRepository()).find(role.getRol());
	}

}
