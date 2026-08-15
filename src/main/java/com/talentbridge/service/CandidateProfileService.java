package com.talentbridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talentbridge.dto.CandidateResponseDTO;
import com.talentbridge.entity.CandidateProfile;
import com.talentbridge.entity.Role;
import com.talentbridge.entity.User;
import com.talentbridge.repository.CandidateProfileRepository;

@Service
public class CandidateProfileService {

	private final CandidateProfileRepository candidateProfileRepository;
	private final UserService userService;
	
	public CandidateProfileService(CandidateProfileRepository candidateProfileRepository,UserService userService) {
		this.candidateProfileRepository=candidateProfileRepository;
		this.userService=userService;
	}
	
	public CandidateProfile createCandidate(
	        User user,
	        CandidateProfile candidateProfile) {

	    user.setRole(Role.CANDIDATE);

	    User savedUser = userService.addUser(user);

	    CandidateProfile newProfile = new CandidateProfile();

	    newProfile.setUser(savedUser);
	    newProfile.setPhone(candidateProfile.getPhone());
	    newProfile.setLocation(candidateProfile.getLocation());
	    newProfile.setEducation(candidateProfile.getEducation());
	    newProfile.setExperience(candidateProfile.getExperience());
	    newProfile.setSkills(candidateProfile.getSkills());

	    return candidateProfileRepository.save(newProfile);
	}
	
	public CandidateProfile updatedCandidate(Long id,CandidateProfile updatedProfile) {
		CandidateProfile existingProfile=candidateProfileRepository.findById(id).orElseThrow();
		existingProfile.setPhone(updatedProfile.getPhone());
		 existingProfile.setLocation(updatedProfile.getLocation());
		    existingProfile.setEducation(updatedProfile.getEducation());
		    existingProfile.setExperience(updatedProfile.getExperience());
		    existingProfile.setSkills(updatedProfile.getSkills());
		    return candidateProfileRepository.save(existingProfile);

	}
	
	public void deleteCandidate(Long id) {

	    CandidateProfile candidateProfile =
	            candidateProfileRepository.findById(id)
	            .orElseThrow();

	    User user = candidateProfile.getUser();

	    candidateProfileRepository.delete(candidateProfile);

	    userService.deleteUser(user.getId());
	}
	
	public CandidateResponseDTO getCandidateById(Long id) {

	    CandidateProfile candidateProfile =
	            candidateProfileRepository.findById(id)
	            .orElseThrow();

	    User user = candidateProfile.getUser();

	    CandidateResponseDTO dto = new CandidateResponseDTO();

	    dto.setId(candidateProfile.getId());

	    dto.setName(user.getName());
	    dto.setEmail(user.getEmail());
	    dto.setRole(user.getRole());

	    dto.setPhone(candidateProfile.getPhone());
	    dto.setLocation(candidateProfile.getLocation());
	    dto.setEducation(candidateProfile.getEducation());
	    dto.setExperience(candidateProfile.getExperience());
	    dto.setSkills(candidateProfile.getSkills());

	    dto.setCreated_at(user.getCreated_at());
	    dto.setUpdated_at(user.getUpdated_at());

	    return dto;
	}
	
	public List<CandidateResponseDTO> getALLCandidates() {

	    List<CandidateProfile> candidates =
	            candidateProfileRepository.findAll();

	    return candidates.stream().map(candidateProfile -> {

	        User user = candidateProfile.getUser();

	        CandidateResponseDTO dto = new CandidateResponseDTO();

	        dto.setId(candidateProfile.getId());

	        dto.setName(user.getName());
	        dto.setEmail(user.getEmail());
	        dto.setRole(user.getRole());

	        dto.setPhone(candidateProfile.getPhone());
	        dto.setLocation(candidateProfile.getLocation());
	        dto.setEducation(candidateProfile.getEducation());
	        dto.setExperience(candidateProfile.getExperience());
	        dto.setSkills(candidateProfile.getSkills());

	        dto.setCreated_at(user.getCreated_at());
	        dto.setUpdated_at(user.getUpdated_at());

	        return dto;

	    }).toList();
	}
	
	public CandidateProfile findCandidateEntityById(Long id) {

	    return candidateProfileRepository.findById(id)
	            .orElseThrow();
	}
	}

