package com.makeathon.dto;

import java.util.List;
import java.util.Set;

public class CampaignDTO {

	private int id;
	
	private String name;
	
	private String tags;
	
	private String createdBy;
	
	private boolean isActive;
	
	private String description;
	
	private String email_list;
	
	private List<Integer> templateIds;
	
	private List<TemplateDTO> templateDTOs;
	
	private Set<TemplateDTO> templatesDTO;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail_list() {
		return email_list;
	}

	public void setEmail_list(String email_list) {
		this.email_list = email_list;
	}

	public List<Integer> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(List<Integer> templateIds) {
		this.templateIds = templateIds;
	}

	public List<TemplateDTO> getTemplateDTOs() {
		return templateDTOs;
	}

	public void setTemplateDTOs(List<TemplateDTO> templateDTOs) {
		this.templateDTOs = templateDTOs;
	}

	public Set<TemplateDTO> getTemplatesDTO() {
		return templatesDTO;
	}

	public void setTemplatesDTO(Set<TemplateDTO> templatesDTO) {
		this.templatesDTO = templatesDTO;
	}
	
}
