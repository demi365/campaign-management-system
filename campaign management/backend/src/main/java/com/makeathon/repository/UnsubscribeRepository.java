package com.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeathon.entity.UnsubcribeLogs;

public interface UnsubscribeRepository extends JpaRepository<UnsubcribeLogs, Integer> {

	public List<UnsubcribeLogs> findByCampaignId(int campaignId);
	
	public UnsubcribeLogs findByCampaignIdAndUserId(int campaignId, String userId);
	
}
