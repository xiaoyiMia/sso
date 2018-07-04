package org.mars.sso.exception;

import org.mars.common.exception.message.CategoryCode;
import org.mars.common.exception.message.ErrorMessageContainer;

public enum RegistrationErrorMessage implements ErrorMessageContainer {
	EMAIL_DUPLICATION("01", "User with same email adress exists"),

	USER_DENIED("02", "User is denied"),

	EMAIL_PASSWORD_NOT_MATCH("03", "Couldn't find record matches the email and password");

	private String detailCode;
	private String description;

	RegistrationErrorMessage(String detailCode, String description) {
		this.detailCode = detailCode;
		this.description = description;
	}

	@Override
	public String getDetailCode() {
		return CategoryCode.Registration.categoryCode() + detailCode;
	}

	@Override
	public String getInternalDetails() {
		return description;
	}

	@Override
	public String getExposureDetails() {
		return this.name();
	}

}
