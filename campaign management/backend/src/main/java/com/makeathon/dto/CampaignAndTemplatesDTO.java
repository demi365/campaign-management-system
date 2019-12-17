package com.makeathon.dto;

import java.util.List;

import com.makeathon.entity.Campaign;
import com.makeathon.entity.Template;

public class CampaignAndTemplatesDTO {

	private List<Campaign> campaigns;
	
	private List<Template> templates;

	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	
	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}
	
}
