package com.talentbridge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CandidateProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String phone;
	private String location;
	private String education;
	private String experience;
	private String skills;
	
	@OneToOne
	private User user;
	
	public CandidateProfile() {
		
	}	
	

}
