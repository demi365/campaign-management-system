package com.makeathon.repository;

import java.util.List;

public interface SubscribersRepositoryCustom {

	public List<String> findAllStates(int orgId, String country);
	
	public List<String> findAllRegions(int orgId, String country, String state);
	
	public List<String> findAllEmails(int orgId, String country, String state, String region);
	
}
