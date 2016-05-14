package com.jdx.admusr.repository.interfaces;

import com.jdx.admusr.model.RoleEntity;
import com.jdx.common.repository.interfaces.GenericRepository;

public interface RoleRepository extends GenericRepository<RoleEntity> {
	public Boolean find(String rolename);
}
