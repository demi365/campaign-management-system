package com.makeathon.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	@NotNull
	private String emailId;
	@NotNull
	private String password;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createDate;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@Column( nullable = false, columnDefinition = "BOOLEAN DEFAULT true" )
	private boolean isActive;
	private String authCode;
	private String tags;
	private int orgId;
	
}
