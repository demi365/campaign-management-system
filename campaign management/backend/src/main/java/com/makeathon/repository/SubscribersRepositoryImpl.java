package com.makeathon.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.makeathon.entity.Subscribers;

public class SubscribersRepositoryImpl implements SubscribersRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<String> findAllStates(int orgId, String country) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
		Root<Subscribers> subscriberRoot = query.from(Subscribers.class);
		
		List<Predicate> filterPredicates = new ArrayList<>();
		filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("orgId"), orgId));
		if(!country.equalsIgnoreCase("all")) 
			filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("country"), country));
		
		query.select(subscriberRoot.get("state")).distinct(true);
		query.where(criteriaBuilder.and(filterPredicates.toArray(new Predicate[0])));
		query.orderBy(criteriaBuilder.asc(subscriberRoot.get("state")));
		
		return entityManager.createQuery(query).getResultList();
		
	}

	@Override
	public List<String> findAllRegions(int orgId, String country, String state) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
		Root<Subscribers> subscriberRoot = query.from(Subscribers.class);
		
		List<Predicate> filterPredicates = new ArrayList<>();
		filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("orgId"), orgId));
		if(!country.equalsIgnoreCase("all")) 
			filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("country"), country));
		if(!state.equalsIgnoreCase("all")) 
			filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("state"), state));
		
		query.select(subscriberRoot.get("region")).distinct(true);
		query.where(criteriaBuilder.and(filterPredicates.toArray(new Predicate[0])));
		query.orderBy(criteriaBuilder.asc(subscriberRoot.get("region")));
		
		return entityManager.createQuery(query).getResultList();
		
	}

	@Override
	public List<String> findAllEmails(int orgId, String country, String state, String region) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
		Root<Subscribers> subscriberRoot = query.from(Subscribers.class);
		
		List<Predicate> filterPredicates = new ArrayList<>();
		filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("orgId"), orgId));
		if(country!=null && !country.equalsIgnoreCase("all")) 
			filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("country"), country));
		if(state!=null && !state.equalsIgnoreCase("all")) 
			filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("state"), state));
		if(region!=null && !region.equalsIgnoreCase("all")) 
			filterPredicates.add(criteriaBuilder.equal(subscriberRoot.get("region"), region));
		
		query.select(subscriberRoot.get("mailBoxId"));
		query.where(criteriaBuilder.and(filterPredicates.toArray(new Predicate[0])));
		query.orderBy(criteriaBuilder.asc(subscriberRoot.get("mailBoxId")));
		
		return entityManager.createQuery(query).getResultList();
		
	}

}
