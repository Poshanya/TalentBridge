package com.talentbridge.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.talentbridge.dto.ApplicationResponseDTO;
import com.talentbridge.entity.Application;
import com.talentbridge.entity.ApplicationStatus;
import com.talentbridge.entity.CandidateProfile;
import com.talentbridge.entity.Job;
import com.talentbridge.exception.ResourceNotFoundException;
import com.talentbridge.repository.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CandidateProfileService candidateProfileService;
    private final JobService jobService;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            CandidateProfileService candidateProfileService,
            JobService jobService) {

        this.applicationRepository = applicationRepository;
        this.candidateProfileService = candidateProfileService;
        this.jobService = jobService;
    }

    public Application applyForJob(
            Long candidateId,
            Long jobId,
            Application application) {

    	CandidateProfile candidate =
    	        candidateProfileService.findCandidateEntityById(candidateId);

    	Job job =
    	        jobService.findJobEntityById(jobId);
    	
    	
        Application newApplication = new Application();

        newApplication.setCandidateProfile(candidate);
        newApplication.setJob(job);
        newApplication.setStatus(ApplicationStatus.APPLIED);
        newApplication.setAppliedAt(LocalDateTime.now());

        return applicationRepository.save(newApplication);
    }

    public List<ApplicationResponseDTO> getAllApplications() {

        List<Application> applications = applicationRepository.findAll();

        return applications.stream().map(application -> {

            ApplicationResponseDTO dto = new ApplicationResponseDTO();

            dto.setId(application.getId());
            dto.setAppliedAt(application.getAppliedAt());
            dto.setUpdatedAt(application.getUpdatedAt());

            // 1. Safe Status Null Check
            if (application.getStatus() != null) {
                dto.setStatus(application.getStatus().toString());
            } else {
                dto.setStatus("APPLIED");
            }

            // 2. Safe Candidate Null Check
            if (application.getCandidateProfile() != null) {
                dto.setCandidateId(application.getCandidateProfile().getId());
                
                // Safe User Null Check
                if (application.getCandidateProfile().getUser() != null) {
                    dto.setCandidateName(application.getCandidateProfile().getUser().getName());
                } else {
                    dto.setCandidateName("Unknown User Account");
                }
            }

            // 3. Safe Job Null Check
            if (application.getJob() != null) {
                dto.setJobId(application.getJob().getId());
                dto.setJobTitle(application.getJob().getTitle());
                dto.setCompanyName(application.getJob().getCompanyName());
            }

            return dto;

        }).toList();
    }

    
    public ApplicationResponseDTO getApplicationById(Long id) {

        Application application =
                applicationRepository.findById(id)
                        .orElseThrow(() ->
    	            	new ResourceNotFoundException("Application not found with id: "+ id)
        	            		);

        ApplicationResponseDTO dto =
                new ApplicationResponseDTO();

        dto.setId(application.getId());
        dto.setStatus(application.getStatus().name());
        dto.setAppliedAt(application.getAppliedAt());
        dto.setUpdatedAt(application.getUpdatedAt());

        dto.setCandidateId(
                application.getCandidateProfile().getId()
        );

        dto.setCandidateName(
                application.getCandidateProfile()
                        .getUser()
                        .getName()
        );

        dto.setJobId(
                application.getJob().getId()
        );

        dto.setJobTitle(
                application.getJob().getTitle()
        );

        dto.setCompanyName(
                application.getJob().getCompanyName()
        );

        return dto;
    }

    // 2. UPDATED: Accepts the strict ApplicationStatus Enum parameter directly
    public Application updateApplication(Long id, ApplicationStatus newStatus) {

        Application existingApplication =
                applicationRepository.findById(id)
                .orElseThrow();

        existingApplication.setStatus(newStatus);
        existingApplication.setUpdatedAt(LocalDateTime.now()); // Automatically sets the update clock

        return applicationRepository.save(existingApplication);
    }

    public void deleteApplication(Long id) {

        Application application =
                applicationRepository.findById(id)
                .orElseThrow();

        applicationRepository.delete(application);
    }
}