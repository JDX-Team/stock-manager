package com.jdx.admusr.repository.interfaces;

import com.jdx.admusr.model.ControllerEntity;
import com.jdx.common.repository.interfaces.GenericRepository;

public interface ControllerRepository extends GenericRepository<ControllerEntity>{

	public ControllerEntity find(String controller);
}
