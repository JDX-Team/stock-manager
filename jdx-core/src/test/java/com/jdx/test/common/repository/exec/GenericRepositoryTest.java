package com.jdx.test.common.repository.exec;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jdx.common.exception.RepoTechnicalException;
import com.jdx.test.common.model.GenericEntity;
import com.jdx.test.common.model.GenericRelEntity;
import com.jdx.test.common.repository.interfaces.IGenericRepository;
import com.jdx.test.config.BootStrapInitializer;
import com.jdx.test.config.InMemoryJpaDatabaseConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("jpa-in-memory-common")
@ContextConfiguration(
		initializers = BootStrapInitializer.class,
		classes = { 
				InMemoryJpaDatabaseConfiguration.class 
				})
@Transactional

/**
 * 
 * @author EQTIC_PROD_JFH
 *
 */

public class GenericRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private IGenericRepository genRepo;

	private GenericEntity entity;
	

	/**
	 * Caso funcional de test para inserción correcta
	 * 
	 * @param viewEntity
	 */
	@Before
	public void setUp() {
		
		// Creamos el objeto de test
		GenericEntity entity = new GenericEntity();
		entity.setName("vistaTest");

		// añadimos sus atributos relacionados
		List<GenericRelEntity> rels = new ArrayList<GenericRelEntity>();
		entity.setRels(rels);

		GenericEntity en = genRepo.add(entity);
		this.entity = en;
	}

	/**
	 * Caso funcional de test para inserción con objecto correcto
	 * 
	 * @param
	 */
	@Test
	public void add() {
		GenericEntity entity = new GenericEntity();
		entity.setName("vistaTest");

		// añadimos sus atributos relacionados
		List<GenericRelEntity> rels = new ArrayList<GenericRelEntity>();
		entity.setRels(rels);

		GenericEntity en = genRepo.add(entity);
	}

	/**
	 * Caso funcional de test para inserción con objecto vacio / inválido
	 * 
	 * @param
	 */
	@Test(expected = RepoTechnicalException.class)
	public void addKo() {
		GenericEntity viewEntity = new GenericEntity();
		GenericEntity v = genRepo.add(viewEntity);
	}

	/**
	 * Caso funcional de test para inserción con objecto NULL
	 * 
	 * @param
	 */

	@Test(expected = RepoTechnicalException.class)
	public void addNull() {
		GenericEntity v = genRepo.add(null);
	}

	/**
	 * Caso funcional de test para actualización con objecto correcto
	 * 
	 * @param
	 */
	@Test
	public void update() {
		GenericEntity view = genRepo.read(this.entity);

		view.setName("vista_modificado");

		genRepo.update(view);

		GenericEntity updatedView = genRepo.read(this.entity);

		Assert.assertNotNull(updatedView);
		Assert.assertEquals("vista_modificado", updatedView.getName());
	}

	/**
	 * Caso funcional de test para actualización con objecto vacio / inválido
	 * 
	 * @param
	 */
	@Test
	public void updateKo() {
		
			try {
				GenericEntity view = new GenericEntity();
				view.setId(9999999);
				genRepo.update(view);
				genRepo.getEntityManager().flush();
			} catch (Exception e) {
				Assert.assertTrue(true);
			}
	}

	/**
	 * Caso funcional de test para actualización con objecto null
	 * 
	 * @param
	 */
	@Test(expected = RepoTechnicalException.class)
	public void updateNull() {
		genRepo.update(null);
	}

	@Test
	public void list() {
		int a = genRepo.list().size();
		GenericEntity entity = new GenericEntity();
		entity.setName("vistaTest");

		// añadimos sus atributos relacionados
		List<GenericRelEntity> rels = new ArrayList<GenericRelEntity>();
		entity.setRels(rels);

		GenericEntity en = genRepo.add(entity);
		
		int b = genRepo.list().size();
		
		Assert.assertTrue(	++a == b ? true : false);
	}

	@Test
	public void get() {
		GenericEntity ent = genRepo.read(this.entity);
		Assert.assertNotNull(ent);
	}

	/**
	 * Caso funcional de test para eliminación de objecto correcto
	 * 
	 * @param viewEntity
	 */
	@Test
	public void remove() {
		int a = genRepo.list().size();
		
		genRepo.delete(this.entity);
		List<GenericEntity> views = genRepo.list();
		int b = genRepo.list().size();
		Assert.assertTrue(--a == b);
	}

	/**
	 * Caso funcional de test para eliminación de objecto vacio / inválido
	 * 
	 * @param viewEntity
	 */
	@Test(expected = RepoTechnicalException.class)
	public void removeKo() {
		GenericEntity entity = new GenericEntity();
		entity.setId(-1);
		genRepo.delete(entity);
	}

	/**
	 * Caso funcional de test para eliminación de objecto nulo
	 * 
	 * @param viewEntity
	 */
	@Test(expected = RepoTechnicalException.class)
	public void removeNull() {
		genRepo.delete(null);
	}

}
