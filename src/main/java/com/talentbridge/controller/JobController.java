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

import com.talentbridge.dto.JobRequestDTO;        // Added DTO Import
import com.talentbridge.dto.JobUpdateRequestDTO;  // Added DTO Import
import com.talentbridge.dto.JobResponseDTO;
import com.talentbridge.entity.Job;
import com.talentbridge.service.JobService;
import jakarta.validation.Valid;                  // Added Validation Import

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // 1. UPDATED FOR POST: Accepts JobRequestDTO with validation
    @PostMapping("/recruiter/{recruiterId}")
    public Job createJob(
            @PathVariable Long recruiterId,
            @Valid @RequestBody JobRequestDTO jobDto) { // Swapped to DTO + added @Valid

        return jobService.createJob(recruiterId, jobDto);
    }

    @GetMapping
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobResponseDTO getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // 2. UPDATED FOR PUT: Accepts JobUpdateRequestDTO with validation
    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobUpdateRequestDTO updatedJobDto) { // Swapped to DTO + added @Valid

        return jobService.updateJob(id, updatedJobDto);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}
