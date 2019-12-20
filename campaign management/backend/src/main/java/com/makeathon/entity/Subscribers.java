package com.makeathon.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Subscribers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@NotNull
	private String srcSysId;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	private boolean isActive;
}
