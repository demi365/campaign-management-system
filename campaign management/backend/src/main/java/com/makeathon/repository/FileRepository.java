package com.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeathon.entity.Images;

public interface FileRepository extends JpaRepository<Images, Integer> {
	
	public List<Images> findByUploadedBy(String userId);
	
}
