package org.mars.sso.model;

import java.time.ZonedDateTime;

import org.mars.common.SoftDeletable;
import org.mars.common.Timestamped;
import org.mars.common.exception.InvalidField;
import org.mars.common.exception.InvalidRequestException;
import org.mars.common.exception.message.InvalidDataMessage;
import org.mars.common.values.EmailAddress;
import org.mars.common.values.Password;
import org.mars.common.values.UserExternalId;
import org.ting.jsonapi.annotations.JsonApiId;
import org.ting.jsonapi.annotations.JsonApiResource;

import lombok.Data;

@Data
@JsonApiResource(type = "user")
public class User implements SoftDeletable, Timestamped {
	@JsonApiId
	private Long id;
	private EmailAddress email;
	private Password password;
	private UserExternalId externalId;
	private UserStatus status;
	private Boolean isDeleted;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;

	public InvalidRequestException validatePayloadForLogin() {
		InvalidRequestException exception = new InvalidRequestException();
		if (this.getEmail() == null) {
			exception.append(new InvalidField("email", InvalidDataMessage.DATA_CANNOT_BLANK));
		}

		if (this.getPassword() == null) {
			exception.append(new InvalidField("password", InvalidDataMessage.DATA_CANNOT_BLANK));
		}
		return exception;
	}

	public InvalidRequestException validatePayloadForRegister() {
		InvalidRequestException exception = new InvalidRequestException();
		if (this.getEmail() == null) {
			exception.append(new InvalidField("email", InvalidDataMessage.DATA_CANNOT_BLANK));
		} else {
			exception.append(this.getEmail().validate("email"));
		}

		if (this.getPassword() == null) {
			exception.append(new InvalidField("password", InvalidDataMessage.DATA_CANNOT_BLANK));
		} else {
			exception.append(this.getPassword().validate("password"));
		}
		return exception;
	}
}
