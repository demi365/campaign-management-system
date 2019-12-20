package com.makeathon.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
public class LinkHits {
	
	@EmbeddedId
	private LinkHitsId linkId;
	private int campaignId;
	private int clicks = 0;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String ipAddresses = "";
	
}
