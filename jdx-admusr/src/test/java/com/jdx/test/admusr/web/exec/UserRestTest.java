package com.jdx.test.admusr.web.exec;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdx.admusr.model.RoleEntity;
import com.jdx.admusr.model.UserEntity;
import com.jdx.admusr.web.UserRestController;
import com.jdx.config.AdmUsrServiceConfig;
import com.jdx.config.AdmUsrWebConfiguration;
import com.jdx.test.common.web.ControllerTest;
import com.jdx.test.config.BootStrapInitializer;
import com.jdx.test.config.InMemoryJpaDatabaseConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("jpa-in-memory")
@ContextConfiguration(
		initializers = BootStrapInitializer.class,
		classes = { 
					InMemoryJpaDatabaseConfiguration.class,
//					LdapConfig.class,
					AdmUsrServiceConfig.class,
					AdmUsrWebConfiguration.class
				})
@Transactional
public class UserRestTest extends ControllerTest {

	@Autowired
	private UserRestController userRestController;
	
	/**
	 * SetUp method, initialize mockMvc with available controllers
	 */
	@Before
	public void setUp() {
		super.setUp(userRestController);
	}
	
	/**
	 * Test that checks userRestController
	 * @throws Exception
	 */
	@Test
	public void validateGetUsers() throws Exception {

		//perform get operation over /users url
		ResultActions result = mockMvc.perform(get("/users")); 
		result		
		.andExpect(status().isOk()) //Check a 200 result
		.andExpect(	content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)) //Check media type result
		.andExpect(jsonPath("$",hasSize(3))) //Check list of users contain 3 users
		.andExpect(jsonPath("$[2].roles",hasSize(1))); //Check that third user contains 1 role
	}
	
	/**
	 * Test that checks userRestController
	 * @throws Exception
	 */
	@Test
	public void validateCreateUser() throws Exception {
		
		//Create new user
		UserEntity userEntity = new UserEntity();
		userEntity.setUser("usuario_prueba2");
		userEntity.setPassword("pwd");

		//perform post operation over /users url
		ResultActions result = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userEntity)));
		result		
		.andExpect(status().isCreated()) //Check a 201 result
		.andExpect(	content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)) //Check media type result
		.andExpect(jsonPath("$.msg",equalTo("Operación realizada."))); //Check result
	}
	
	/**
	 * Test that checks userRestController
	 * @throws Exception
	 */
	@Test
	public void validateCreateUserInvalid() throws Exception {
		
		//Create new user
		UserEntity userEntity = new UserEntity();
		userEntity.setUser("usuario_no_ldap");
		userEntity.setPassword("pwd");

		//perform post operation over /users url
		ResultActions result = mockMvc.perform(
				post("/users")
				.contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userEntity))
                ); 
		result		
		.andExpect(status().isUnprocessableEntity()) //Check a 422 result
		.andExpect(	content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)) //Check media type result
		.andExpect(jsonPath("$.errorMsg",equalTo("El usuario no existe en LDAP."))); //Check result
	}
	
	/**
	 * Test that checks userRestController
	 * @throws Exception
	 */
	@Test
	public void validateGetUser() throws Exception {
		//perform get operation over /users/1 url
		ResultActions result = mockMvc.perform(get("/users/1")); 
		result		
		.andExpect(status().isOk()) //Check a 200 result
		.andExpect(	content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)) //Check media type result
		//Check output {"id":1,"user":"root","fecMod":1450825200000,"roles":[]}
		.andExpect(jsonPath("$.id",equalTo(1)))
		.andExpect(jsonPath("$.user",equalTo("root")))
		.andExpect(jsonPath("$.fecMod",equalTo(1450825200000L)))
		.andExpect(jsonPath("$.roles",hasSize(0))); //Check result
	}
	
	/**
	 * Test that checks userRestController
	 * @throws Exception
	 */
	@Test
	public void validatePutUser() throws Exception {
		//Create existing user with a new role association
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		userEntity.setUser("root");
		userEntity.setFecMod(new Timestamp(new Date(1450825200000L).getTime()));
		
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(1);
		
		userEntity.setRoles(Arrays.asList(roleEntity));
		
		
		//perform get operation over /users/1 url
		ResultActions result = mockMvc.perform(
				put("/users/1")
				.contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userEntity))
                ); 
		result		
		.andExpect(status().isOk()) //Check a 200 result
		.andExpect(	content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//Check media type result
		.andExpect(jsonPath("$.msg",equalTo("Operación realizada."))); //Check result; 
	}
	
	/**
	 * Test that checks userRestController
	 * @throws Exception
	 */
	@Test
	public void validateDelUser() throws Exception {
		
		//perform delete operation over /users/1 url
		ResultActions result = mockMvc.perform(
				delete("/users/1")
                ); 
		result		
		.andExpect(status().isOk()) //Check a 200 result
		.andExpect(	content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//Check media type result
		.andExpect(jsonPath("$.msg",equalTo("Operación realizada."))); //Check result; 
	}
}
