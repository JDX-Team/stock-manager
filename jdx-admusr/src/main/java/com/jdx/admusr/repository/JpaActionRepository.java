package com.jdx.admusr.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jdx.admusr.model.ActionEntity;
import com.jdx.admusr.repository.interfaces.ActionRepository;
import com.jdx.common.repository.DefaultGenericRepository;

@Repository
public class JpaActionRepository extends DefaultGenericRepository<ActionEntity> implements ActionRepository{

	@Override
	public ActionEntity find(String action) {
		Session session = getEntityManager().unwrap(Session.class);
		
		Criteria objCriteria  = session.createCriteria(ActionEntity.class).add(Restrictions.eq("action", action)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;
		
		ActionEntity result = (ActionEntity) objCriteria.uniqueResult();
		
		return result;
	}

	
}
