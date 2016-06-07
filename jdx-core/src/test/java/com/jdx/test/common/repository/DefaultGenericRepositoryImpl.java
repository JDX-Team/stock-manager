package com.jdx.test.common.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdx.common.repository.DefaultGenericRepository;
import com.jdx.test.common.model.GenericEntity;
import com.jdx.test.common.repository.interfaces.IGenericRepository;

/**
 * Abstract class with generic behavior for repository layer
 *
 * @param <E> managed entity
 */
@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class DefaultGenericRepositoryImpl extends DefaultGenericRepository<GenericEntity> implements IGenericRepository{


}
