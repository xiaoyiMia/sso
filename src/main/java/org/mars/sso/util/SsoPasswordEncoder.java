package org.mars.sso.util;

import org.mars.common.MarsPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SsoPasswordEncoder implements MarsPasswordEncoder{

	private PasswordEncoder passwordEncoder;
	
	@Override
	public String encode(CharSequence originalPassword) {
		return passwordEncoder.encode(originalPassword);
	}

	@Override
	public boolean matches(CharSequence originalPassword, String hashedPassword) {
		return passwordEncoder.matches(originalPassword, hashedPassword);
	}

	
}
