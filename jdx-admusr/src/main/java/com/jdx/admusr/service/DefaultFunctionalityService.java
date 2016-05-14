package com.jdx.admusr.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.jdx.admusr.model.FunctionalityEntity;
import com.jdx.admusr.repository.interfaces.FunctionalityRepository;
import com.jdx.admusr.service.interfaces.FunctionalityService;
import com.jdx.common.service.DefaultGenericService;


@Service
public class DefaultFunctionalityService extends DefaultGenericService<FunctionalityEntity> implements FunctionalityService {

	public List<FunctionalityEntity> list(FunctionalityEntity entity){
		return ((FunctionalityRepository)getRepository()).list(entity);
	}
	

	@Override
	public Set<String> getRights(String username) {
		return ((FunctionalityRepository)getRepository()).getRights(username);
	}

	
}
