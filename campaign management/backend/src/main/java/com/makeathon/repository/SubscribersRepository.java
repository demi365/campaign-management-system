package com.makeathon.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.makeathon.entity.Subscribers;

public interface SubscribersRepository extends JpaRepository<Subscribers, Integer>, SubscribersRepositoryCustom {
	
	public Set<Subscribers> findByOrgId(int orgId);

	@Query("select distinct country from Subscribers s where s.orgId = ?1")
	public Set<String> findDistinctCountryValuesByOrgId(int orgId);
	
}
