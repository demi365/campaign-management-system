package com.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeathon.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Integer>{
	
	public List<Work> findByUploadedBy(String uploadedBy);
	
}
