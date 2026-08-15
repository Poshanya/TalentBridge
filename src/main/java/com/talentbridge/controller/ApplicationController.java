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

import com.talentbridge.dto.ApplicationResponseDTO;
import com.talentbridge.entity.Application;
import com.talentbridge.service.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/candidate/{candidateId}/job/{jobId}")
    public Application applyForJob(
            @PathVariable Long candidateId,
            @PathVariable Long jobId,
            @RequestBody Application application) {

        return applicationService.applyForJob(
                candidateId,
                jobId,
                application);
    }

    @GetMapping
    public List<ApplicationResponseDTO> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationResponseDTO getApplicationById(
            @PathVariable Long id) {

        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}")
    public Application updateApplication(
            @PathVariable Long id,
            @RequestBody Application application) {

        return applicationService.updateApplication(
                id,
                application);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(
            @PathVariable Long id) {

        applicationService.deleteApplication(id);
    }
}