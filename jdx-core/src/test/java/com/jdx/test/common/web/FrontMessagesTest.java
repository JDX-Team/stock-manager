package com.jdx.test.common.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.jdx.common.web.FrontMessagesController;
import com.jdx.test.config.BootStrapInitializer;
import com.jdx.test.config.InMemoryJpaDatabaseConfiguration;
import com.jdx.test.config.WebMvcConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("jpa-in-memory-common")
@ContextConfiguration(
		initializers = BootStrapInitializer.class,
		classes = { 
					InMemoryJpaDatabaseConfiguration.class,
					WebMvcConfiguration.class
				})
@Transactional

public class FrontMessagesTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private FrontMessagesController frontMessageController;
	
	private MockMvc mockMvc;
	
	/**
	 * SetUp method, initialize mockMvc with available controllers
	 */
	@Before
	public void setUp() {
		
		mockMvc = standaloneSetup(frontMessageController)
	            .build();
		
	}
	
	/**
	 * Test that checks freontMessageController
	 * @throws Exception
	 */
	@Test
	public void validateFrontMessages() throws Exception {

		mockMvc.perform(get("/messages")) //perform get operation over /messages url
				.andExpect(status().isOk()) //Check a 200 result
				.andExpect(	content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)) //Check media type result
				.andExpect(jsonPath("$.['example.title']").value("Title")); //Check correct output
	}


}
