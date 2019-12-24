package com.makeathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makeathon.dto.FeedbackDTO;
import com.makeathon.service.FeedbacksService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/feedback")
@Api(value = "receiving feedback API endpoints", tags = { "Feedback" })
public class FeedbackController {

	@Autowired
	FeedbacksService feedbackService;
	
	@GetMapping(value="/campaign/{campaign_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FeedbackDTO> getAllFeedbacksForCampaign(@PathVariable("campaign_id") int campaignId) {
		return feedbackService.getAllFeedbacks(campaignId);
	}
	

	@PutMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public FeedbackDTO addFeedbackForCampaign(@RequestBody FeedbackDTO feedbackDTO) {
		return feedbackService.addFeedback(feedbackDTO);
	}
	
}
