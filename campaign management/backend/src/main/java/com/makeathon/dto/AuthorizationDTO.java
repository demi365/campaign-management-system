package com.makeathon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationDTO {
	
	private String authorizationCode;
	private int orgId;
	
}
