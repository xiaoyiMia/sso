package org.mars.sso.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mars.sso.controllers.AppController;
import org.mars.sso.mapper.UserMapper;
import org.mars.sso.model.Authentication;
import org.mars.sso.model.PasswordRegisterPayload;
import org.mars.sso.model.User;
import org.mars.sso.service.RegisterService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AppControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
  private ObjectMapper objectMapper;

	@InjectMocks
	private AppController appController;
	@MockBean
	private RegisterService registerService;
	@MockBean
	private UserMapper userMapper;
	@MockBean
	private User user;

	@Test
	public void testRegisterSuccessful() throws JSONException, Exception {

		given(userMapper.toUser(any(PasswordRegisterPayload.class))).willReturn(user);
		given(registerService.registerWithPassword(user)).willReturn(user);
		
		Authentication authentication = RegisterContext.generateAuthentication();
		given(registerService.generateAuthentication(user)).willReturn(authentication);

		PasswordRegisterPayload payload = RegisterContext.buildPasswordRegisterPayload();

		this.mockMvc
		    .perform(post("/apps")
		        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(payload)))
		    .andExpect(status().isCreated())
		    .andDo(document("email-password-register", preprocessResponse(prettyPrint()),
		        requestFields(fieldWithPath("email").description("The register email"),
		            fieldWithPath("password").description("The register password"),
		            fieldWithPath("name").description("The user name"))));
	}
}
