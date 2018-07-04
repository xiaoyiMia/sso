package org.mars.sso.service;

import org.mars.sso.exception.RegistrationErrorMessage;
import org.mars.sso.exception.RegistrationException;
import org.mars.sso.mapper.UserMapper;
import org.mars.sso.model.Authentication;
import org.mars.sso.model.User;
import org.mars.sso.persistence.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterServiceImpl implements RegisterService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(User loginInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User registerWithPassword(User registerInfo) {
		UserEntity userEntity = userRepository.findByEmail(registerInfo.getEmail().email());
		if(userEntity == null) {
			userEntity = userMapper.toUserEntity(registerInfo);
			userRepository.save(userEntity);
			return userMapper.toUser(userEntity);
		} else {
			throw RegistrationException.create(RegistrationErrorMessage.EMAIL_DUPLICATION); 
		}
	}

	@Override
	public Authentication generateAuthentication(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
