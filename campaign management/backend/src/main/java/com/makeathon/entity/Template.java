package com.makeathon.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Template {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@Lob
	@Type(type="org.hibernate.type.TextType")
	@NotNull
	private String html;
	private String tags;
	private String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@Transient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "template")
	private List<Feedbacks> feebacks;
	@ManyToOne(fetch = FetchType.LAZY)
	private Campaign campaign;
}
