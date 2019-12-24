package com.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeathon.entity.FeedbackId;
import com.makeathon.entity.Feedbacks;

public interface FeedbacksRepository extends JpaRepository<Feedbacks, FeedbackId>{
	
	public List<Feedbacks> findByCampaignId(int campaignId);
	
}
