package com.makeathon.dto;

import java.util.List;

import com.makeathon.entity.Campaign;
import com.makeathon.entity.Template;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignAndTemplatesDTO {

	private List<Campaign> campaigns;
	
	private List<Template> templates;
}
