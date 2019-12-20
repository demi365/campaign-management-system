package com.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.makeathon.entity.LinkHits;
import com.makeathon.entity.LinkHitsId;

public interface LinkHitsRepository extends JpaRepository<LinkHits, LinkHitsId> {

	@Query("select l from LinkHits l where linkId.workId = ?1")
	List<LinkHits> findByworkId(int workId);
	
}
