package com.jdx.admusr.repository;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Repository;

import com.jdx.admusr.model.RoleEntity;
import com.jdx.admusr.model.UserEntity;
import com.jdx.admusr.repository.interfaces.UserRepository;
import com.jdx.common.repository.DefaultGenericRepository;

@Repository
public class JpaUserRepository extends DefaultGenericRepository<UserEntity> implements UserRepository{


	@Override
	public UserEntity add(UserEntity user) {
		
		Session session = getEntityManager().unwrap(Session.class);
		
		//Si tiene roles refresca contra la session de hibernate
		if(user.getRoles()!=null){
			for (RoleEntity role : user.getRoles()) {
				session.refresh(role);
			}
		}
		
		session.persist(user);
		
		return user;
		
	}
	
	/**
	 * Método que comprueba si el usuario dado existe en LDAP
	 * 
	 * @param nombre nombre de usuario
	 * 
	 * @return true si el usuario existe en LDAP
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public Boolean checkNombre(String nombre) {
		
//		EqualsFilter filter = new EqualsFilter("uid", nombre);
//
//		List list = ldapTemplate.search("", filter.encode(),SearchScope.SUBTREE.getId(), new AttributesMapper() {
//			public Object mapFromAttributes(Attributes attrs) throws NamingException {
//				return attrs.get("uid").get();
//			}
//		});
		//TODO implementar comprobación contra base de datos

		return true;
	}

	@Override
	public UserEntity find(String username) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria objCriteria  = session.createCriteria(UserEntity.class).add(Restrictions.eq("user", username)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;
		
		UserEntity result = (UserEntity) objCriteria.uniqueResult();
		
		return result;
		
	}
	
	
	
}
