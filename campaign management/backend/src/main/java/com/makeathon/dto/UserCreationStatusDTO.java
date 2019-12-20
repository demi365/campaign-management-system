package com.makeathon.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreationStatusDTO {

	private Date createTime;
	private int createdUsers;
	private int updatedUsers;
	private int errorCount;
	private String message;
	
}
