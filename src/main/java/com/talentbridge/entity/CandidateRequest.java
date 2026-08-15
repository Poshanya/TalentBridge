package com.talentbridge.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateRequest {

	private User user;
	private CandidateProfile candidateProfile;
}
