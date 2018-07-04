package org.mars.sso.exception;

import org.mars.common.exception.ExceptionLevel;
import org.mars.sso.model.UserStatus;
import org.mars.sso.persistence.AppUserEntity;

/**
 * User Denied Exception. By Default, it generate a warning level exception with
 * Register category, error message indicate the user resource is marked as
 * 'denied' in system.
 * 
 * @author xiaoyi
 *
 */
public class UserDeniedException extends RegistrationException {

	private static final long serialVersionUID = -7692897522368367962L;

	public UserDeniedException() {
		super(ExceptionLevel.Warning);
		this.setErrorMessage(RegistrationErrorMessage.USER_DENIED);
	}

	public static void validateUserStatus(AppUserEntity appuUser) {
		if(appuUser.getStatus() == UserStatus.DENY) {
			throw new UserDeniedException();
		}
	}
}
