package com.makeathon.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@IdClass(FeedbackId.class)
public class Feedbacks {
	
	@Id
	private int campaignId;
	@Id
	private int templateId;
	@Id
	private int userId;
	private int rating;
	private String feedbackComments;
	@Transient
	@ManyToOne
	private Template template;
}
