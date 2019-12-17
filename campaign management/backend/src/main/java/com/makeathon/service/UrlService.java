package com.makeathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makeathon.entity.UnsubcribeLogs;
import com.makeathon.repository.UnsubscribeRepository;

@Service
public class UrlService {

	@Autowired
	UnsubscribeRepository urlRepo;
	
	public String insertIntoUrl(int campaignId, String userId) {
		
		if(urlRepo.findByCampaignIdAndUserId(campaignId, userId) == null) {

			UnsubcribeLogs unsubs = new UnsubcribeLogs();
			unsubs.setCampaignId(campaignId);
			unsubs.setUserId(userId);
			urlRepo.save(unsubs);
			
			return "You have successfully unsubscribed from this service";
			
		} else { 
			return "You have already subscribed for this campaign";
		}
		
	}
	
}
