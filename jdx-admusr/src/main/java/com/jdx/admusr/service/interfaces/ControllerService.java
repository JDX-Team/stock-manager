package com.jdx.admusr.service.interfaces;

import com.jdx.admusr.model.ControllerEntity;
import com.jdx.common.service.interfaces.GenericService;

public interface ControllerService extends GenericService<ControllerEntity> {
	
	public ControllerEntity find(String controller);

}
