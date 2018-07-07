package org.mars.sso.controller;

import java.time.ZonedDateTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.mars.common.values.EmailAddress;
import org.mars.common.values.Password;
import org.mars.sso.model.Authentication;
import org.mars.sso.model.PasswordRegisterPayload;

public class RegisterContext {
	
	public static PasswordRegisterPayload buildPasswordRegisterPayload() {
		PasswordRegisterPayload payload = new PasswordRegisterPayload();
		payload.setEmail(EmailAddress.create("ting@guiker.com"));
		payload.setName("ting");
		payload.setPassword(Password.create("12345678"));
		return payload;
	}
	
	public static String generateSerializedPayload(PasswordRegisterPayload payload) throws JSONException {
		return new JSONObject().put("email", payload.getEmail().email()).put("password", payload.getPassword().password()).put("name", payload.getName()).toString();
	}
	
	public static Authentication generateAuthentication() {
		ZonedDateTime now = ZonedDateTime.now();
		Authentication authentication = new Authentication("testing-token", 1L);
		authentication.setExpiresAt(now.plusMinutes(30));
		authentication.setIssuedAt(now);
		authentication.setMustValidateAt(now.plusMinutes(5));
		return authentication;
	}
	
	
}
