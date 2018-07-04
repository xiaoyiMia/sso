package org.mars.sso.exception;

import org.mars.common.exception.ExceptionLevel;

/**
 * No user record matches with the email and password exception. By default,
 * warning level with unprocessable HTTP status
 * 
 * @author xiaoyi
 *
 */
public class EmailPasswordNotMatchException extends RegistrationException {

	private static final long serialVersionUID = 1012802006386531918L;

	public EmailPasswordNotMatchException() {
		super(ExceptionLevel.Warning);
		this.setErrorMessage(RegistrationErrorMessage.EMAIL_PASSWORD_NOT_MATCH);
	}
}
