package com.talentbridge.dto;

import java.time.LocalDateTime;

import com.talentbridge.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateResponseDTO {

    private Long id;

    private String name;
    private String email;
    private Role role;

    private String phone;
    private String location;
    private String education;
    private String experience;
    private String skills;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}