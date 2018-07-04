package org.mars.sso;

import org.mars.common.exception.InvalidField;
import org.mars.common.exception.message.InvalidDataMessage;
import org.springframework.http.HttpStatus;
import org.ting.jsonapi.ExceptionResponseContainer;

public class InvalidRequestResponse implements ExceptionResponseContainer{

	private String title;
	private String detailCode;
	private String details;
	private String invalidPayloadAttribute;
	private String invalidQueryParam;
	
	public InvalidRequestResponse(InvalidField invalidField) {
		InvalidDataMessage errorMessage = invalidField.getErrorMessage();
		this.title = errorMessage.getExposureDetails();
		this.details = errorMessage.getInternalDetails();
		this.detailCode = errorMessage.getDetailCode();
		
		if(invalidField.isInvalidPayload()) {
		this.invalidPayloadAttribute = invalidField.getFeildFullName();
		} else {
			this.invalidQueryParam = invalidField.getFeildFullName();
		}
	}
	
	@Override
	public String _getStatusCode() {
		return HttpStatus.BAD_REQUEST.toString();
	}
	
	@Override
	public String _getDetailCode() {
		return this.detailCode;
	}
	
	@Override
	public String _getErrorTitle() {
		return this.title;
	}
	
	@Override
	public String _getErrorDetail() {
		return this.details;
	}
	
	@Override
	public String _getInvalidPayloadAttribute() {
		return this.invalidPayloadAttribute;
	}
	
	@Override
	public String _getInvalidQueryParameter() {
		return this.invalidQueryParam;
	}
}
