package com.makeathon.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
@Entity
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	@NotNull
	private String emailId;
	@NotNull
	private String password;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createDate;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@Column( nullable = false, columnDefinition = "BOOLEAN DEFAULT true" )
	private boolean isActive;
	private String authCode;
	private String tags;
	private int orgId;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ALL"));
		return authorities;
	}
	@Override
	public String getUsername() {
		return getEmailId();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
