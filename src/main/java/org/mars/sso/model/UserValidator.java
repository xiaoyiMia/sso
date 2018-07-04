package org.mars.sso.model;

import org.mars.common.exception.InvalidField;
import org.mars.common.exception.InvalidRequestException;
import org.mars.common.exception.MarsException;
import org.mars.common.exception.message.InvalidDataMessage;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
	
	/**
	 * Validate request payload for register and login
	 * @param user Contains email and password
	 */
	public void validatePayloadForLogin(User user) {
		if (user == null) {
			InvalidRequestException.throwException(new InvalidField(null, InvalidDataMessage.DATA_CANNOT_BLANK));
		} else {
			MarsException.throwException(user.validatePayloadForLogin());
		}
	}
	
	public void validatePayloadForRegister(User user) {
		if (user == null) {
			InvalidRequestException.throwException(new InvalidField(null, InvalidDataMessage.DATA_CANNOT_BLANK));
		} else {
			MarsException.throwException(user.validatePayloadForRegister());
		}
	}
}
