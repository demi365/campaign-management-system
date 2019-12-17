package com.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeathon.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	
	public List<Campaign> findByName(String name);

	public List<Campaign> findByCreatedBy(String createdBy);
	
}
