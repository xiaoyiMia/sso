package org.mars.sso.service;

import org.mars.sso.persistence.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

	public UserEntity findByEmailAndPassword(String email, String password);
	
	public UserEntity findByEmail(String email);
}
