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

import com.talentbridge.dto.CandidateResponseDTO;
import com.talentbridge.dto.CandidateUpdateRequestDTO;
import com.talentbridge.entity.CandidateProfile;
import com.talentbridge.entity.CandidateRequestDTO;
import com.talentbridge.service.CandidateProfileService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/candidates")
public class CandidateProfileController {

    private final CandidateProfileService candidateProfileService;
    

    public CandidateProfileController(
            CandidateProfileService candidateProfileService) {

        this.candidateProfileService = candidateProfileService;
    }

    @PostMapping
    public CandidateProfile createCandidate(
          @Valid  @RequestBody CandidateRequestDTO request) {

        return candidateProfileService.createCandidate(
                request.getUser(),
                request.getCandidateProfile());
    }

    @GetMapping("/{id}")
    public CandidateResponseDTO getCandidateById(@PathVariable Long id) {
        return candidateProfileService.getCandidateById(id);
    }

    @PutMapping("/{id}")
    public CandidateProfile updateCandidate(
            @PathVariable Long id,
           @Valid @RequestBody CandidateUpdateRequestDTO updatedProfile) {

        return candidateProfileService.updatedCandidate(
                id, updatedProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(
            @PathVariable Long id) {

        candidateProfileService.deleteCandidate(id);
    }
    
    @GetMapping
    public List<CandidateResponseDTO> getALLCandidates() {
        return candidateProfileService.getALLCandidates();
    }
}