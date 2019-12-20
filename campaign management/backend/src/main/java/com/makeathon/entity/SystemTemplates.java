package com.makeathon.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Data
public class SystemTemplates {
	
	@Id
	private String name;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String html;
	
}
