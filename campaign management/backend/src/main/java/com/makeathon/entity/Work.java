package com.makeathon.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Work {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String uploadedBy;
	@Lob
	@Type(type="org.hibernate.type.TextType")
	private String html;
	@Lob
	@Type(type="org.hibernate.type.TextType")
	private String emailList;
	private String status;
	
}
