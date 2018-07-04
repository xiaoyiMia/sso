package org.mars.sso.service;

import org.mars.sso.model.Authentication;
import org.mars.sso.model.User;

public interface RegisterService {

	public User login(User loginInfo);
	
	public User registerWithPassword(User registerInfo);

	public Authentication generateAuthentication(User user);
	
	
}
