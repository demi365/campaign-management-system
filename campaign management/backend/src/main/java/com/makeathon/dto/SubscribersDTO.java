package com.makeathon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribersDTO {
	
	private int id;
	private String firstName;
	private String lastName;
	private String mailBoxId;
	private String phoneNumber;
	private String address;
	private String region;
	private String state;
	private String country;
	private int age;
	private String gender;
	private int orgId;
	private String srcSysId;
	private boolean isActive;
	
}
