package com.makeathon.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private int id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private Date dateOfBirth;
	private String authCode;
	private int orgId;
	
}
