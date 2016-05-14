package com.jdx.admusr.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jdx.admusr.model.ControllerEntity;
import com.jdx.admusr.repository.interfaces.ControllerRepository;
import com.jdx.common.repository.DefaultGenericRepository;

@Repository
public class JpaControllerRepository extends DefaultGenericRepository<ControllerEntity> implements ControllerRepository{

	@Override
	public ControllerEntity find(String controller) {
		Session session = getEntityManager().unwrap(Session.class);
		
		Criteria objCriteria  = session.createCriteria(ControllerEntity.class).add(Restrictions.eq("controller", controller)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;
		
		ControllerEntity result = (ControllerEntity) objCriteria.uniqueResult();
		
		return result;
	}

	
}
