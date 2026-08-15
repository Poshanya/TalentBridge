package com.talentbridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentbridge.entity.CandidateProfile;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Long> {

}
