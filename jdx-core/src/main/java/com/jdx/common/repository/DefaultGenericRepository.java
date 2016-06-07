package com.jdx.common.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdx.common.annotation.DefaultFecthProfile;
import com.jdx.common.repository.criterion.LikeExpresionJDX;
import com.jdx.common.repository.interfaces.GenericRepository;

/**
 * Abstract class with generic behavior for repository layer
 *
 * @param <E>
 *            managed entity
 */
// Mandatory transactionality, support a current transaction, throw an exception
// if none exists.
@Transactional(propagation = Propagation.MANDATORY)

public abstract class DefaultGenericRepository<E> implements GenericRepository<E> {

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private MessageSource messageSource;

	protected Class<E> entityClass;

	@PersistenceContext
	private EntityManager entityManager;

	private final static Logger LOGGER = LoggerFactory.getLogger(DefaultGenericRepository.class);

	@SuppressWarnings("unchecked")
	protected DefaultGenericRepository() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	/**
	 * Method for list all entities
	 * 
	 * @return list of entities
	 */
	public List<E> list() {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria objCriteria = session.createCriteria(entityClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<E> result = objCriteria.list();

		return result;
	}

	/**
	 * Method for read an entity given his entity
	 * 
	 * @param entity
	 * @return found entity
	 */
	public E read(E entity) {

		E result = getEntityManager().find(entityClass, entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity));

		return result;

	}

	/**
	 * Basic method for persist given entity
	 * 
	 * @param entity
	 *            entity to persist
	 */
	public E add(E entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	/**
	 * Basic method for update given entity
	 * 
	 * @param entity
	 *            to update
	 */
	public void update(E entity) {
		Session sesion = (SessionImpl) getEntityManager().getDelegate();
		sesion.update(entity);

	}

	/**
	 * Basic method for delete entity
	 * 
	 * @param entity
	 *            to delete
	 */
	public void delete(E entity) {
		getEntityManager().remove(entity);
	}

	protected String getMessage(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}

	protected String getMessage(String message, String[] args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}

	public ApplicationContext getAppContext() {
		return appContext;
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected String getDefaultFetchProfile() {
		DefaultFecthProfile[] annotations = this.entityClass.getAnnotationsByType(DefaultFecthProfile.class);

		if (annotations.length > 0 && annotations[0].value() != "")
			return annotations[0].value();
		else
			return null;
	}

	/**
	 * Method for list all entities
	 * 
	 * @return list of entities
	 */
	public List<E> list(E entity) {
		Criteria objCriteria = getCriteriaFiltered(entity, null).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<E> result = objCriteria.list();

		return result;
	}

	private Criteria getCriteriaFiltered(Object entity, Criteria criteria) {
		Session session = entityManager.unwrap(Session.class);

		Criteria objCriteria = criteria == null ? session.createCriteria(entityClass) : criteria;

		Field[] fields = entity.getClass().getDeclaredFields();

		// Si nos llegan filtros los aplicamos

		for (int i = 0; i < fields.length; i++) {
			try {
				// get value from field entity
				Object value = PropertyUtils.getProperty(entity, fields[i].getName());

				if (value != null) {
					// if field is an collection call method recursively
					if (fields[i].getType().isAssignableFrom(List.class)) {
						objCriteria = getCriteriaFiltered(((List<?>) value).get(0),
								objCriteria.createCriteria(fields[i].getName()));
					} else if (fields[i].getType().isAnnotationPresent(Entity.class)) {
						// Si el campo es una entidad
						objCriteria = getCriteriaFiltered(value, objCriteria.createCriteria(fields[i].getName()));
					} else if (fields[i].getType().isAssignableFrom(Date.class)) {
						// Si el campo es un date se filtra con equal
						objCriteria.add(Restrictions.eq(fields[i].getName(), value));
					} else if (fields[i].getType().isAssignableFrom(String.class)) {
						// Si es string se filtra con like %value%
						objCriteria
								.add(new LikeExpresionJDX(fields[i].getName(), value.toString(), MatchMode.ANYWHERE));
					} else {
						// Para el resto de campos se filtra con equal
						objCriteria.add(Restrictions.eq(fields[i].getName(), value));
					}
				}
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// No existe el campo en la entidad, se ignora
				LOGGER.error("No existe la columna '" + fields[i].getName() + "' en la entidad '"
						+ entityClass.getSimpleName() + "'.");
			}
		}

		return objCriteria;
	}

}
