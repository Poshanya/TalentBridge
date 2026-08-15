package com.talentbridge.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
	
	private String status;
	private LocalDateTime appliedAt;
	private LocalDateTime updatedAt;
		
	
	@ManyToOne
	private CandidateProfile candidateProfile;
	
	@ManyToOne
	private Job job;
	
	public Application() {
		
	}
	
	

}
