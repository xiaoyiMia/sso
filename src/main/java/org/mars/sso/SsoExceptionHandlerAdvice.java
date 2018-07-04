package org.mars.sso;

import java.util.ArrayList;
import java.util.List;

import org.mars.common.exception.InvalidField;
import org.mars.common.exception.InvalidRequestException;
import org.mars.common.exception.message.CommonErrorMessage;
import org.mars.sso.exception.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.ting.jsonapi.converter.JsonConverter;
import org.ting.jsonapi.response.JsonResponse;

@ControllerAdvice
public class SsoExceptionHandlerAdvice {

	@ExceptionHandler(value = InvalidRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody JsonResponse handleInvalidRequestException(InvalidRequestException exception) {
		List<InvalidRequestResponse> response = new ArrayList<InvalidRequestResponse>();
		for (InvalidField invalidField : exception.getInvalidFields()) {
			response.add(new InvalidRequestResponse(invalidField));
		}
		return JsonConverter.convert(response);
	}

	@ExceptionHandler(value = RegistrationException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody JsonResponse handleRegistrationException(RegistrationException exception) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY, exception.getErrorMessage());
		return JsonConverter.convert(response);
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	public @ResponseBody JsonResponse handleUnknownException(Exception exception) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE, CommonErrorMessage.UNKNOWN_EXCEPTION);
		return JsonConverter.convert(response);
	}
}
