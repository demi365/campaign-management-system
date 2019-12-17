package com.makeathon.dto;

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
	
	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignDescription() {
		return campaignDescription;
	}

	public void setCampaignDescription(String campaignDescription) {
		this.campaignDescription = campaignDescription;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}
	
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}

	public String[] getTemplateTags() {
		return templateTags;
	}

	public void setTemplateTags(String[] templateTags) {
		this.templateTags = templateTags;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String[] getCampaignTags() {
		return campaignTags;
	}

	public void setCampaignTags(String[] campaignTags) {
		this.campaignTags = campaignTags;
	}

	public String[] getEmail_list() {
		return email_list;
	}

	public void setEmail_list(String[] email_list) {
		this.email_list = email_list;
	}
	
}
