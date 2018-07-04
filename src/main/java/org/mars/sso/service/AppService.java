package org.mars.sso.service;

import org.mars.sso.persistence.AppEntity;

public interface AppService {

	public AppEntity createApp(String appName);
	
	public void createFail();
	
	public AppEntity getApp(Long appId);
	
	public void getAppUsers(AppEntity app);
}
