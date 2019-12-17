package com.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeathon.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Integer>{
	
	public Template findByName(String name);

	public List<Template> findByCreatedBy(String createdBy);
	
}
