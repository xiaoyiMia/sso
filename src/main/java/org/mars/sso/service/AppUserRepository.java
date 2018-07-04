package org.mars.sso.service;

import org.mars.sso.persistence.AppUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUserEntity, Long> {

}
