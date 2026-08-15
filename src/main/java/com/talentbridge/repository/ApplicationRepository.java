package com.talentbridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentbridge.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
