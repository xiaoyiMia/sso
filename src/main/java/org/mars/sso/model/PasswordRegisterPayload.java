package org.mars.sso.model;

import org.mars.common.exception.InvalidRequestException;
import org.mars.common.exception.message.InvalidDataMessage;
import org.mars.common.values.EmailAddress;
import org.mars.common.values.Password;
import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class PasswordRegisterPayload {

	private static int NAME_MAX_LENGTH = 10;
	
	private EmailAddress email;
	private Password password;
	private String name;
	
	public InvalidRequestException validate() {
		InvalidRequestException exception = new InvalidRequestException();
		if(email == null) {
			exception.append("email", InvalidDataMessage.DATA_CANNOT_BLANK);
		} else {
			exception.append(email.validate("email"));
		}
		
		if(password == null) {
			exception.append("password", InvalidDataMessage.DATA_CANNOT_BLANK);
		} else {
			exception.append(password.validate("email"));
		}
		
		if(!StringUtils.hasText(this.name)) {
			exception.append("name", InvalidDataMessage.DATA_CANNOT_BLANK);
		} else if(name.length() > NAME_MAX_LENGTH) {
			exception.append("name", InvalidDataMessage.DATA_TOO_LONG);
		}
		return exception;
	}
	
}
