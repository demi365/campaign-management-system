package com.makeathon.dto;

import lombok.Data;

@Data
public class FeedbackDTO {

	private int campaignId;
	private int templateId;
	private int userId;
	private int rating;
	private String feedbackComments;
}
