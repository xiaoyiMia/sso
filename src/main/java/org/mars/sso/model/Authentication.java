package org.mars.sso.model;

import java.time.ZonedDateTime;

import org.ting.jsonapi.annotations.JsonApiId;
import org.ting.jsonapi.annotations.JsonApiResource;

import lombok.Data;

@Data
@JsonApiResource(type = "authentication")
public class Authentication{
	
	private String tokenId;
	@JsonApiId
	private Long userId;
	private ZonedDateTime issuedAt;
	private ZonedDateTime expiresAt;
	private ZonedDateTime mustValidateAt;
	
	public Authentication(String tokenId, Long userId) {
		this.tokenId = tokenId;
		this.userId = userId;
	}
}
