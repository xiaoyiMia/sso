package org.mars.sso;

import org.mars.common.exception.message.ErrorMessageContainer;
import org.springframework.http.HttpStatus;
import org.ting.jsonapi.ExceptionResponseContainer;

public class ExceptionResponse implements ExceptionResponseContainer{

	private HttpStatus statusCode;
	private String title;
	private String detailCode;
	private String details;
	
	public ExceptionResponse(HttpStatus statusCode, ErrorMessageContainer errorMessage) {
		this.statusCode = statusCode;
		this.title = errorMessage.getExposureDetails();
		this.details = errorMessage.getInternalDetails();
		this.detailCode = errorMessage.getDetailCode();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void cleanDetails() {
		this.details = null;
	}
	
	@Override
	public String _getStatusCode() {
		return this.statusCode.toString();
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
}
