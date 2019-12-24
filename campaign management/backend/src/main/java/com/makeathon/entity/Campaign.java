package com.makeathon.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Campaign {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	private String tags;
	@NotNull
	private String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@Column( nullable = false, columnDefinition = "BOOLEAN DEFAULT true" )
	private boolean isActive;
	@NotNull
	private String description;
	@NotNull
	@Lob
	@Type(type="org.hibernate.type.TextType")
	private String email_list;
	@OneToMany(targetEntity = Template.class)
	private Set<Template> templates = new HashSet<Template>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campaignId")
    private List<Feedbacks> feedbacks;
	
}
