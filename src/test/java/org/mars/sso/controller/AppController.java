package org.mars.sso.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mars.sso.mapper.UserMapper;
import org.mars.sso.model.PasswordRegisterPayload;
import org.mars.sso.service.RegisterService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
//@Import(UserRegistrationContext.class)
public class AppController {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private AppController appController;
	@MockBean
	private RegisterService registerService;
	@MockBean
	private UserMapper userMapper;
	
	@Test
	public void testRegisterSuccessful() {
	}
}
