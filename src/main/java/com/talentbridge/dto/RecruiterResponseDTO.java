package com.talentbridge.dto;

import java.time.LocalDateTime;

import com.talentbridge.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterResponseDTO {

    private Long id;

    private String name;
    private String email;
    private Role role;

    private String CompanyName;
    private String location;
    private String designation;
    private String phone;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}