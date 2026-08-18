package com.talentbridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentbridge.dto.RecruiterResponseDTO;
import com.talentbridge.dto.RecruiterUpdateRequestDTO;
import com.talentbridge.entity.RecruiterProfile;
import com.talentbridge.entity.RecruiterRequestDTO;
import com.talentbridge.entity.User;
import com.talentbridge.service.RecruiterProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recruiters")
public class RecruiterProfileController {

	
	private final RecruiterProfileService recruiterProfileService;
	
	public RecruiterProfileController(RecruiterProfileService recruiterProfileService) {
		this.recruiterProfileService=recruiterProfileService;
	}
	
	@GetMapping("/{id}")
	public RecruiterResponseDTO getRecruiterById(
	        @PathVariable Long id) {

	    return recruiterProfileService.getRecruiterById(id);
	}
	
	@PostMapping
	public RecruiterProfile createRecruiterProfile(User user,@Valid @RequestBody RecruiterRequestDTO request) {
		
		return recruiterProfileService.createRecruiter(
				request.getUser(),
				request.getRecruiterProfile()
				);
	}
	@PutMapping("/{id}")
	public RecruiterProfile updateRecruiter(
	        @PathVariable Long id,
	        @Valid @RequestBody RecruiterUpdateRequestDTO updatedProfile) {

	    return recruiterProfileService.updateRecruiter(
	            id, updatedProfile);
	}
		
		@DeleteMapping("/{id}")
		public void  deleteRecruiterProfile(@PathVariable Long id) {
			recruiterProfileService.deleteRecruiter(id);
		}
		
		
		@GetMapping
		public List<RecruiterResponseDTO> getAllRecruiters() {

		    return recruiterProfileService.getAllRecruiters();
		}
}

