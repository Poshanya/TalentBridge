package com.talentbridge.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.talentbridge.dto.JobResponseDTO;
import com.talentbridge.entity.Job;
import com.talentbridge.entity.RecruiterProfile;
import com.talentbridge.repository.JobRepository;

@Service	
public class JobService {

	private final JobRepository jobRepository;
	private final RecruiterProfileService recruiterProfileService;
	
	public JobService(JobRepository jobRepository,RecruiterProfileService recruiterProfileService) {
		this.recruiterProfileService=recruiterProfileService;
		this.jobRepository=jobRepository;
	}
	
	public Job createJob(Long recruiterId, Job job) {

		RecruiterProfile recruiter =
		        recruiterProfileService.findRecruiterEntityById(recruiterId);

	    Job newJob = new Job();

	    newJob.setTitle(job.getTitle());
	    newJob.setCompanyName(job.getCompanyName());
	    newJob.setDescription(job.getDescription());
	    newJob.setSkills(job.getSkills());
	    newJob.setLocation(job.getLocation());
	    newJob.setSalary(job.getSalary());

	    newJob.setRecruiter(recruiter);
	    newJob.setCreatedAt(LocalDateTime.now());
	    newJob.setUpdatedAt(LocalDateTime.now());

	    return jobRepository.save(newJob);
	}
	public Job updateJob(Long id, Job updatedJob) {

	    Job existingJob = jobRepository.findById(id)
	            .orElseThrow();

	    existingJob.setTitle(updatedJob.getTitle());
	    existingJob.setCompanyName(updatedJob.getCompanyName());
	    existingJob.setDescription(updatedJob.getDescription());
	    existingJob.setLocation(updatedJob.getLocation());
	    existingJob.setSalary(updatedJob.getSalary());
	    existingJob.setSkills(updatedJob.getSkills());

	    existingJob.setUpdatedAt(LocalDateTime.now());

	    return jobRepository.save(existingJob);
	}
	
	public void deleteJob(Long id) {
	
		Job deletejob=jobRepository.findById(id).orElseThrow();
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
