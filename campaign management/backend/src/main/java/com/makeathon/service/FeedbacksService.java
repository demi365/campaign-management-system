package com.makeathon.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makeathon.dto.DTOFactory;
import com.makeathon.dto.FeedbackDTO;
import com.makeathon.entity.Feedbacks;
import com.makeathon.repository.FeedbacksRepository;

@Service
public class FeedbacksService {

	@Autowired
	FeedbacksRepository feedbackRepo;
	
	public List<FeedbackDTO> getAllFeedbacks(int campaignId) {
		List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
		feedbackRepo.findByCampaignId(campaignId).stream()
					.forEach(feedback -> feedbackDTOs.add(DTOFactory.getFeedbackDTO(feedback)));
		return feedbackDTOs;
		
	}

	@Transactional
	public FeedbackDTO addFeedback(FeedbackDTO feedbackDTO) {
		Feedbacks feedback = DTOFactory.getFeedbackEntity(feedbackDTO);
		feedbackRepo.save(feedback);
		return DTOFactory.getFeedbackDTO(feedback);
	}

}
