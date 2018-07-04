package org.mars.sso;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.ting.jsonapi.converter.JsonConverter;
import org.ting.jsonapi.response.JsonResponse;

@RestControllerAdvice
public class ssoResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
	    Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body== null) {
			response.setStatusCode(HttpStatus.NO_CONTENT);
			return body;
		}else if (body instanceof Page) {
			Page<?> pageObject = (Page<?>) body;
			JsonResponse jsonResponse = JsonConverter.convert(pageObject.getContent());
			jsonResponse.addMeta(generatePageMeta(pageObject));
			return jsonResponse;
		} else {
			return JsonConverter.convert(body);
		}

	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		Type returnValueType = returnType.getGenericParameterType();
		if(returnValueType.getTypeName().equals("void")) {
			return true;
		}
		
		try {
			if (returnValueType instanceof ParameterizedType) {
				ParameterizedType paramType = ((ParameterizedType) returnType.getGenericParameterType());
				returnValueType = paramType.getRawType();

				Class<?> rawTypeClass = Class.forName(returnValueType.getTypeName());
				if (rawTypeClass.isAssignableFrom(Map.class)) {
					return false;
				} else if (rawTypeClass.isAssignableFrom(Iterator.class)) {
					returnValueType = paramType.getActualTypeArguments()[0];
				}
			}

			Class<?> actualTypeClass = Class.forName(returnValueType.getTypeName());
			if (actualTypeClass.isAssignableFrom(PayloadSample.class)) {
				return false;
			} else if (actualTypeClass.isAssignableFrom(JsonResponse.class)) {
				return false;
			} else if (actualTypeClass.isAssignableFrom(ResponseEntity.class)) {
				return false;
			}

			String typeString = returnValueType.getTypeName();
			String classString = ResponseEntity.class.getTypeName();
			System.out.println(typeString.equals(classString));
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private Map<String, Object> generatePageMeta(Page<?> pageObject) {
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("total", pageObject.getTotalElements());
		meta.put("number", pageObject.getNumberOfElements());
		meta.put("totalPage", pageObject.getTotalPages());
		meta.put("perPage", pageObject.getSize());
		meta.put("currentPage", pageObject.getNumber());
		return meta;
	}

}
