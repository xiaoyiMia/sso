package org.mars.sso.controllers;

import org.mars.sso.model.Authentication;
import org.mars.sso.model.User;
import org.mars.sso.model.UserValidator;
import org.mars.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private RegisterService userService;
	
	@Autowired
	private UserValidator userValidator;
//	@Autowired
//	private UserMapper userMapper;
	
	@PostMapping("/login")
	public Authentication login(@RequestBody User loginPayload) {
		return null;
//		userValidator.validatePayloadForLogin(loginPayload);
//		
//		return userService.login(loginPayload);
	}
}
