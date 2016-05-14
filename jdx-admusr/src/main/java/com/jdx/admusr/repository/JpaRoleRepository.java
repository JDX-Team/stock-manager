package com.jdx.admusr.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jdx.admusr.model.RoleEntity;
import com.jdx.admusr.repository.interfaces.RoleRepository;
import com.jdx.common.repository.DefaultGenericRepository;

@Repository
public class JpaRoleRepository extends DefaultGenericRepository<RoleEntity> implements RoleRepository{

	@Override
	public Boolean find(String rolename) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria objCriteria  = session.createCriteria(RoleEntity.class).add(Restrictions.eq("rol", rolename)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;
		
		RoleEntity result = (RoleEntity) objCriteria.uniqueResult();
		
		return result == null ? true : false ;
		
	}

}
