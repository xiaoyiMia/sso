package org.mars.sso.exception;

import org.mars.common.exception.ExceptionLevel;
import org.mars.common.exception.MarsException;

public class RegistrationException extends MarsException{

	private static final long serialVersionUID = 1974995229324750706L;

	private RegistrationErrorMessage errorMessage;
	
	/**
	 * Create WARNING level registration exception
	 * @param errorMessage
	 * @return
	 */
	public static RegistrationException create(RegistrationErrorMessage errorMessage) {
		RegistrationException exception = new RegistrationException(ExceptionLevel.Warning);
		exception.setErrorMessage(errorMessage);
		return exception;
	}
	
	public RegistrationException(ExceptionLevel level) {
		super(level);
	}

	public RegistrationErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(RegistrationErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

}
