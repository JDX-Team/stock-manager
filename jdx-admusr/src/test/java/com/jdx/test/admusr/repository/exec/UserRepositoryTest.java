package com.jdx.test.admusr.repository.exec;

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

import com.jdx.admusr.model.RoleEntity;
import com.jdx.admusr.model.UserEntity;
import com.jdx.admusr.repository.interfaces.RoleRepository;
import com.jdx.admusr.repository.interfaces.UserRepository;
import com.jdx.test.config.BootStrapInitializer;
import com.jdx.test.config.InMemoryJpaDatabaseConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("jpa-in-memory")
@ContextConfiguration(initializers = BootStrapInitializer.class,classes = {InMemoryJpaDatabaseConfiguration.class})
@Transactional
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	 @Autowired
	 private UserRepository userRepo;
	 @Autowired
	 private RoleRepository roleRepo;
	 
	 private UserEntity entity;
	 	 
	 @Before
	 public void setUp() {
		entity = new UserEntity();
		 
		entity.setUser("usuario_prueba2");
		entity.setPassword("pwd");
		
		List<RoleEntity> roles = roleRepo.list();
		  
		entity.setRoles(roles);
		 
		UserEntity u = userRepo.add(entity);
		
		entity.setId(u.getId());
	 }
	 
	 @Test
	 public void listUser(){
		 List<UserEntity> users = userRepo.list();
		 
		 Assert.assertEquals(4, users.size());
	 }
	 
	 @Test
	 public void getUser(){
		 UserEntity user = userRepo.read(entity);
		 
		 Assert.assertNotNull(user);
		 
		 Assert.assertEquals(roleRepo.list().size(), user.getRoles().size());
	 }
	 
	 @Test
	 public void updateUser(){
		 UserEntity user = userRepo.read(entity);
		 
		 user.setUser("usuario_modificado");
		 user.setPassword("pwd");
		 
		 userRepo.update(user);
		 
		 UserEntity updatedUser = userRepo.read(entity);
		 
		 Assert.assertNotNull(updatedUser);
		 
		 Assert.assertEquals("usuario_modificado", updatedUser.getUser());
	 }
	 
	 @Test
	 public void removeUser(){

		 userRepo.delete(entity);
		 
		 List<UserEntity> users = userRepo.list();
		 
		 Assert.assertEquals(3, users.size());
	 }
	 
	 

}
