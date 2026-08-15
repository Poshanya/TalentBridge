package com.talentbridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentbridge.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
