package com.jdx.admusr.service.interfaces;

import com.jdx.admusr.model.ActionEntity;
import com.jdx.common.service.interfaces.GenericService;

public interface ActionService extends GenericService<ActionEntity> {
	
	public ActionEntity find(String action);

}
