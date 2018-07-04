package org.mars.sso.controllers;

import org.mars.sso.mapper.UserMapper;
import org.mars.sso.model.Authentication;
import org.mars.sso.model.PasswordRegisterPayload;
import org.mars.sso.model.User;
import org.mars.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apps")
public class AppController {

	@Autowired
	private RegisterService registerService;
	@Autowired
	private UserMapper userMapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Authentication register(@RequestBody @NonNull PasswordRegisterPayload payload) {
		payload.validate().throwException();
		
		User user = userMapper.toUser(payload);
		user = registerService.registerWithPassword(user);
		
		return registerService.generateAuthentication(user);
	}
	
	
}
