package com.jdx.test.admusr.repository.exec;

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

import com.jdx.admusr.model.RoleEntity;
import com.jdx.admusr.repository.interfaces.RoleRepository;
import com.jdx.common.config.basic.BootStrapInitializer;
import com.jdx.test.config.InMemoryJpaDatabaseConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("jpa-in-memory")
@ContextConfiguration(initializers = BootStrapInitializer.class,classes = {InMemoryJpaDatabaseConfiguration.class})
@Transactional
public class RoleRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	 @Autowired
	 private RoleRepository roleRepo;
	 
	 private int id;
	 	 
	 @Before
	 public void setUp() {
		
		RoleEntity roleEntity = new RoleEntity();
		//Rol id 1 -- > SuperAdmin
		roleEntity.setId(1);
		roleEntity.setRol("AdminNew");
		
		 
		RoleEntity role = roleRepo.add(roleEntity);
		
		this.id= role.getId();
	 }
	 
	 @Test
	 public void listRole(){
		 List<RoleEntity> roles = roleRepo.list();
		 
		 Assert.assertEquals(3, roles.size());
	 }
	 
	 @Test
	 public void getRole(){
		 RoleEntity role = roleRepo.read(this.id);
		 
		 Assert.assertNotNull(role);
	 }
	 
	 @Test
	 public void updateRole(){
		 RoleEntity role = roleRepo.read(this.id);
		 
		 role.setRol("Role_mod");
		 
		 roleRepo.update(role);
		 
		 RoleEntity updatedRole = roleRepo.read(this.id);
		 
		 Assert.assertNotNull(updatedRole);
		 
		 Assert.assertEquals("Role_mod",updatedRole.getRol());
	 }
	 
	 @Test
	 public void removeRole(){

		 roleRepo.delete(this.id);
		 
		 List<RoleEntity> roles = roleRepo.list();
		 
		 Assert.assertEquals(2, roles.size());
	 }
	 
}
