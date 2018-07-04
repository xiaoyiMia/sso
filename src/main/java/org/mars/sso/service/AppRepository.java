package org.mars.sso.service;

import org.mars.sso.persistence.AppEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppRepository extends CrudRepository<AppEntity, Long> {
	
}
