package com.makeathon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignTemplateDTO {

	private int campaignId;
	
	private String campaignName;
	
	private String campaignDescription;
	
	private String[] campaignTags;
	
	private String[] email_list;
	
	private int templateId;
	
	private String templateName;
	
	private String html;
	
	private String[] templateTags;
	
	private String createdBy;
	
}
