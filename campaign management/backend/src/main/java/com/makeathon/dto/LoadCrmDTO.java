package com.makeathon.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadCrmDTO {
	
	private String authorizationCode;
	private int orgId;
	private Set<SubscribersDTO> customers;
	
}
