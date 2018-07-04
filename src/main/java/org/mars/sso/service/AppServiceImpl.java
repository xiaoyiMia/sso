package org.mars.sso.service;

import org.mars.sso.persistence.AppEntity;
import org.mars.sso.persistence.AppUserEntity;
import org.mars.sso.persistence.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppServiceImpl implements AppService{

	@Autowired
	private AppRepository appRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Override
	public AppEntity createApp(String appName) {
		AppEntity app = new AppEntity();
		app.setName(appName);
		appRepository.save(app);
		
		UserEntity user = new UserEntity();
		user.setEmail("test@google.com");
		user.setPassword("12345678");
		userRepository.save(user);
		
		AppUserEntity appUser = new AppUserEntity();
		appUser.setApp(app);
		appUser.setUser(user);
		appUserRepository.save(appUser);
		
		user.getAppUsers().add(appUser);
		app.getAppUsers().add(appUser);
		return app;
	}
	
	@Override
	@Transactional
	public void createFail() {
		throw new Error("This is a creation Error");
	}

	@Override
	@Transactional
	public AppEntity getApp(Long appId) {
		return appRepository.findById(appId).get();
	}

	@Override
	@Transactional
	public void getAppUsers(AppEntity app) {
		String email = app.getAppUsers().stream().findFirst().get().getUser().getEmail();
		System.out.println(email);
	}

	
}
