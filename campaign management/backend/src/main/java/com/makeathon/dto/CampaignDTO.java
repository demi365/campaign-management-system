package com.makeathon.dto;

import java.util.List;
import java.util.Set;

import com.makeathon.entity.Feedbacks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignDTO {

	private int id;
	
	private String name;
	
	private String tags;
	
	private String createdBy;
	
	private boolean isActive;
	
	private String description;
	
	private String email_list;
	
	private List<Integer> templateIds;
	
	private Set<TemplateDTO> templateDTOs;
	
	private List<Feedbacks> feedbacks;
}
