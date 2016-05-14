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
import com.jdx.admusr.repository.interfaces.UserRepository;
import com.jdx.common.config.basic.BootStrapInitializer;
import com.jdx.test.config.InMemoryJpaDatabaseConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("jpa-in-memory")
@ContextConfiguration(initializers = BootStrapInitializer.class,classes = {InMemoryJpaDatabaseConfiguration.class})
@Transactional
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	 @Autowired
	 private UserRepository userRepo;
	 
	 private int id;
	 	 
	 @Before
	 public void setUp() {
		UserEntity userEntity = new UserEntity();
		 
		userEntity.setUser("usuario_prueba2");
		RoleEntity roleEntity = new RoleEntity();
		//Rol id 1 -- > SuperAdmin
		roleEntity.setId(1);
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		 
		roles.add(roleEntity);
		 
		userEntity.setRoles(roles);
		 
		UserEntity u = userRepo.add(userEntity);
		
		this.id= u.getId();
	 }
	 
	 @Test
	 public void listUser(){
		 List<UserEntity> users = userRepo.list();
		 
		 Assert.assertEquals(4, users.size());
	 }
	 
	 @Test
	 public void getUser(){
		 UserEntity user = userRepo.read(this.id);
		 
		 Assert.assertNotNull(user);
		 
		 Assert.assertEquals(1, user.getRoles().size());
	 }
	 
	 @Test
	 public void updateUser(){
		 UserEntity user = userRepo.read(this.id);
		 
		 user.setUser("usuario_modificado");
		 
		 userRepo.update(user);
		 
		 UserEntity updatedUser = userRepo.read(this.id);
		 
		 Assert.assertNotNull(updatedUser);
		 
		 Assert.assertEquals("usuario_modificado",updatedUser.getUser());
	 }
	 
	 @Test
	 public void removeUser(){

		 userRepo.delete(this.id);
		 
		 List<UserEntity> users = userRepo.list();
		 
		 Assert.assertEquals(3, users.size());
	 }
	 
	 

}
