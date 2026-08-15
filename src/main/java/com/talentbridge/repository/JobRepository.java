package com.talentbridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentbridge.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
