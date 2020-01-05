package com.makeathon.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class FeedbackId implements Serializable {

	/**
	 * auto generated default serial id
	 */
	private static final long serialVersionUID = 1L;
	private int campaignId;
	private int userId;
	private int templateId;
}
