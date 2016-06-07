package com.jdx.master.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jdx.common.repository.DefaultGenericRepository;
import com.jdx.master.repository.interfaces.MasterRepository;


@Repository
public class JpaMasterRepository<E> extends DefaultGenericRepository<E> implements MasterRepository<E>{

//	@Override
//	public E read(E entity) {
//		Session session = getEntityManager().unwrap(Session.class);
//		
//		Criteria objCriteria  = session.createCriteria(entityClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;
//		
//		@SuppressWarnings("unchecked")
//		E result = (E) objCriteria.uniqueResult();
//		
//		return result;
//	}
//
//	@Override
//	public void delete(E entity) {
//		// TODO Auto-generated method stub
//		
//	}

	

	
}
