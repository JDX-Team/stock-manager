package com.jdx.admusr.service;

import org.springframework.stereotype.Service;

import com.jdx.admusr.model.ControllerEntity;
import com.jdx.admusr.repository.interfaces.ControllerRepository;
import com.jdx.admusr.service.interfaces.ControllerService;
import com.jdx.common.service.DefaultGenericService;


@Service
public class DefaultControllerService extends DefaultGenericService<ControllerEntity> implements ControllerService {

	@Override
	public ControllerEntity find(String controller) {
		return ((ControllerRepository)getRepository()).find(controller);
		
	}


	
}
