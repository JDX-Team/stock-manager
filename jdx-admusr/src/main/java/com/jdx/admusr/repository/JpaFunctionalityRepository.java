package com.jdx.admusr.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jdx.admusr.model.FunctionalityEntity;
import com.jdx.admusr.repository.interfaces.FunctionalityRepository;
import com.jdx.common.repository.DefaultGenericRepository;

@Repository
public class JpaFunctionalityRepository extends DefaultGenericRepository<FunctionalityEntity> implements FunctionalityRepository{


	@Override
	public Set<String> getRights(String username) {
		
		//Query for all functionalities available for provided user
		Session session = getEntityManager().unwrap(Session.class);
	
		Criteria objCriteria  = session.createCriteria(FunctionalityEntity.class).createCriteria("roles").createCriteria("users").add(Restrictions.eq("user",username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;
		
		@SuppressWarnings("unchecked")
		List<FunctionalityEntity> result = objCriteria.list();
		
		Set<String> rights = new HashSet<String>();
		
		//Fill the rights set with all user rights
		for (FunctionalityEntity func : result) {
			//right = {Controller.name}.{Action.name}
			rights.add(func.getController().getController()+"."+func.getAction().getAction());
		}
		
		return rights;
		
	}
}
