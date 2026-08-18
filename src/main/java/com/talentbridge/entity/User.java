package com.talentbridge.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min=8)	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;	
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public User() {
		
	}
	
	
	
	

}
