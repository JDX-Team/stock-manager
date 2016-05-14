package com.jdx.admusr.service;

import org.springframework.stereotype.Service;

import com.jdx.admusr.model.ActionEntity;
import com.jdx.admusr.repository.interfaces.ActionRepository;
import com.jdx.admusr.service.interfaces.ActionService;
import com.jdx.common.service.DefaultGenericService;


@Service
public class DefaultActionService extends DefaultGenericService<ActionEntity> implements ActionService {

	@Override
	public ActionEntity find(String action) {
		return ((ActionRepository)getRepository()).find(action);
	}


	
}
