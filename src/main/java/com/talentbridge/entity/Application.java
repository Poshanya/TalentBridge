package com.talentbridge.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status; // Changed from String to ApplicationStatus

	private LocalDateTime appliedAt;
	private LocalDateTime updatedAt;
		
	
	@ManyToOne
	private CandidateProfile candidateProfile;
	
	@ManyToOne
	private Job job;
	
	
	
	public Application() {
	}
	
	

}
