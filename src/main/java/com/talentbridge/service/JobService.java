package com.talentbridge.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.talentbridge.dto.JobRequestDTO;        // Added your DTO import
import com.talentbridge.dto.JobUpdateRequestDTO;  // Added your DTO import
import com.talentbridge.dto.JobResponseDTO;
import com.talentbridge.entity.Job;
import com.talentbridge.entity.RecruiterProfile;
import com.talentbridge.repository.JobRepository;

@Service	
public class JobService {

	private final JobRepository jobRepository;
	private final RecruiterProfileService recruiterProfileService;
	
	public JobService(JobRepository jobRepository, RecruiterProfileService recruiterProfileService) {
		this.recruiterProfileService = recruiterProfileService;
		this.jobRepository = jobRepository;
	}
	
	// 1. POST MATCHING YOUR STYLE: Takes your JobRequestDTO, returns Job Entity
	public Job createJob(Long recruiterId, JobRequestDTO jobDto) {

		RecruiterProfile recruiter =
		        recruiterProfileService.findRecruiterEntityById(recruiterId);

	    Job newJob = new Job();

	    // Mapping fields directly from your JobRequestDTO
	    newJob.setTitle(jobDto.getTitle());
	    newJob.setCompanyName(jobDto.getCompanyName());
	    newJob.setDescription(jobDto.getDescription());
	    newJob.setSkills(jobDto.getSkills());
	    newJob.setLocation(jobDto.getLocation());
	    newJob.setSalary(jobDto.getSalary());

	    newJob.setRecruiter(recruiter);
	    newJob.setCreatedAt(LocalDateTime.now());
	    newJob.setUpdatedAt(LocalDateTime.now());

	    return jobRepository.save(newJob);
	}

	// 2. PUT MATCHING YOUR STYLE: Takes your JobUpdateRequestDTO, returns Job Entity
	public Job updateJob(Long id, JobUpdateRequestDTO updatedJobDto) {

	    Job existingJob = jobRepository.findById(id)
	            .orElseThrow();

	    // Mapping fields directly from your JobUpdateRequestDTO
	    existingJob.setTitle(updatedJobDto.getTitle());
	    existingJob.setCompanyName(updatedJobDto.getCompanyName());
	    existingJob.setDescription(updatedJobDto.getDescription());
	    existingJob.setLocation(updatedJobDto.getLocation());
	    existingJob.setSalary(updatedJobDto.getSalary());
	    existingJob.setSkills(updatedJobDto.getSkills());

	    existingJob.setUpdatedAt(LocalDateTime.now());

	    return jobRepository.save(existingJob);
	}
	
	public void deleteJob(Long id) {
	
		Job deletejob = jobRepository.findById(id).orElseThrow();
		jobRepository.delete(deletejob);
	
	}
	
	public List<JobResponseDTO> getAllJobs() {

	    List<Job> jobs = jobRepository.findAll();

	    return jobs.stream().map(job -> {

	        JobResponseDTO dto = new JobResponseDTO();

	        dto.setId(job.getId());
	        dto.setTitle(job.getTitle());
	        dto.setCompanyName(job.getCompanyName());
	        dto.setDescription(job.getDescription());
	        dto.setLocation(job.getLocation());
	        dto.setSalary(job.getSalary());
	        dto.setSkills(job.getSkills());

	        dto.setRecruiterId(job.getRecruiter().getId());
	        dto.setRecruiterName(
	                job.getRecruiter().getUser().getName()
	        );

	        dto.setCreatedAt(job.getCreatedAt());
	        dto.setUpdatedAt(job.getUpdatedAt());

	        return dto;

	    }).toList();
	}
	
	public JobResponseDTO getJobById(Long id) {

	    Job job = jobRepository.findById(id)
	            .orElseThrow();

	    JobResponseDTO dto = new JobResponseDTO();

	    dto.setId(job.getId());
	    dto.setTitle(job.getTitle());
	    dto.setCompanyName(job.getCompanyName());
	    dto.setDescription(job.getDescription());
	    dto.setLocation(job.getLocation());
	    dto.setSalary(job.getSalary());
	    dto.setSkills(job.getSkills());

	    dto.setRecruiterId(job.getRecruiter().getId());
	    dto.setRecruiterName(
	            job.getRecruiter().getUser().getName()
	    );

	    dto.setCreatedAt(job.getCreatedAt());
	    dto.setUpdatedAt(job.getUpdatedAt());

	    return dto;
	}
	
	public Job findJobEntityById(Long id) {

	    return jobRepository.findById(id)
	            .orElseThrow();
	}
}
