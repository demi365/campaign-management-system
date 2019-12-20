package com.makeathon.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LinkHitsId implements Serializable {

	/**
	 * default generated serial id
	 */
	private static final long serialVersionUID = 5584555311557675906L;
	private int workId;
	private String inputId;
	
}
