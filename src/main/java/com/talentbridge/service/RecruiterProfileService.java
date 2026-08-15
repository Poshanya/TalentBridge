package com.talentbridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talentbridge.dto.RecruiterResponseDTO;
import com.talentbridge.entity.RecruiterProfile;
import com.talentbridge.entity.Role;
import com.talentbridge.entity.User;
import com.talentbridge.repository.RecruiterProfileRepository;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UserService userService;

    public RecruiterProfileService(
            RecruiterProfileRepository recruiterProfileRepository,
            UserService userService) {

        this.recruiterProfileRepository = recruiterProfileRepository;
        this.userService = userService;
    }
    public RecruiterProfile createRecruiter(
            User user,
            RecruiterProfile recruiterProfile) {

        user.setRole(Role.RECRUITER);

        User savedUser = userService.addUser(user);

        RecruiterProfile newProfile = new RecruiterProfile();

        newProfile.setUser(savedUser);
        newProfile.setCompanyName(recruiterProfile.getCompanyName());
        newProfile.setLocation(recruiterProfile.getLocation());
        newProfile.setDesignation(recruiterProfile.getDesignation());
        newProfile.setPhone(recruiterProfile.getPhone());
        newProfile.setRole(Role.RECRUITER);

        return recruiterProfileRepository.save(newProfile);
    }
    
    public RecruiterResponseDTO getRecruiterById(Long id) {

        RecruiterProfile recruiterProfile =
                recruiterProfileRepository.findById(id)
                .orElseThrow();

        User user = recruiterProfile.getUser();

        RecruiterResponseDTO dto = new RecruiterResponseDTO();

        dto.setId(recruiterProfile.getId());

        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        dto.setCompanyName(recruiterProfile.getCompanyName());
        dto.setLocation(recruiterProfile.getLocation());
        dto.setDesignation(recruiterProfile.getDesignation());
        dto.setPhone(recruiterProfile.getPhone());

        dto.setCreated_at(recruiterProfile.getCreatedAt());
        dto.setUpdated_at(recruiterProfile.getUpdatedAt());

        return dto;
    }
    

    public RecruiterProfile updateRecruiter(
            Long id,
            RecruiterProfile updatedProfile) {

        RecruiterProfile existingProfile =
                recruiterProfileRepository.findById(id)
                .orElseThrow();

        existingProfile.setCompanyName(updatedProfile.getCompanyName());
        existingProfile.setLocation(updatedProfile.getLocation());
        existingProfile.setDesignation(updatedProfile.getDesignation());
        existingProfile.setPhone(updatedProfile.getPhone());

        return recruiterProfileRepository.save(existingProfile);
    }

    public void deleteRecruiter(Long id) {

        RecruiterProfile recruiterProfile =
                recruiterProfileRepository.findById(id)
                .orElseThrow();

        User user = recruiterProfile.getUser();

        recruiterProfileRepository.delete(recruiterProfile);

        userService.deleteUser(user.getId());
    }
    
    
    public List<RecruiterResponseDTO> getAllRecruiters() {

        List<RecruiterProfile> recruiters =
                recruiterProfileRepository.findAll();

        return recruiters.stream().map(recruiterProfile -> {

            User user = recruiterProfile.getUser();

            RecruiterResponseDTO dto = new RecruiterResponseDTO();

            dto.setId(recruiterProfile.getId());

            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());

            dto.setCompanyName(recruiterProfile.getCompanyName());
            dto.setLocation(recruiterProfile.getLocation());
            dto.setDesignation(recruiterProfile.getDesignation());
            dto.setPhone(recruiterProfile.getPhone());

            dto.setCreated_at(recruiterProfile.getCreatedAt());
            dto.setUpdated_at(recruiterProfile.getUpdatedAt());

            return dto;

        }).toList();
    }
    public RecruiterProfile findRecruiterEntityById(Long id) {

        return recruiterProfileRepository.findById(id)
                .orElseThrow();
    }
}