package com.makeathon.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
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
	private int userId;
	private int rating;
	private String feedbackComments;
	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "id")
	private Campaign campaign;
}
