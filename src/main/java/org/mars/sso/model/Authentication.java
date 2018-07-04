package org.mars.sso.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Authentication{
	
	private String tokenId;
	private Long userId;
	private ZonedDateTime issuedAt;
	private ZonedDateTime expiresAt;
	private ZonedDateTime mustValidateAt;
	
	public Authentication(String tokenId, Long userId) {
		this.tokenId = tokenId;
		this.userId = userId;
	}
}
