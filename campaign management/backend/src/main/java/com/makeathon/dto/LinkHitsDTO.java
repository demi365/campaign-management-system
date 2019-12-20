package com.makeathon.dto;

import com.makeathon.entity.LinkHitsId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkHitsDTO {

	private LinkHitsId linkId;
	private int campaignId;
	private int clicks = 0;
	private String[] ipAddresses;
	
}
