package com.jdx.admusr.repository.interfaces;

import com.jdx.admusr.model.ActionEntity;
import com.jdx.common.repository.interfaces.GenericRepository;

public interface ActionRepository extends GenericRepository<ActionEntity>{

	public ActionEntity find(String action);
}
