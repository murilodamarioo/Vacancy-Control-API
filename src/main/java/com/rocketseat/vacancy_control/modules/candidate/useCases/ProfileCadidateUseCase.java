package com.rocketseat.vacancy_control.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocketseat.vacancy_control.modules.candidate.CandidateRepository;
import com.rocketseat.vacancy_control.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCadidateUseCase {
  
  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID candidateId) {
    var candidate = this.candidateRepository.findById(candidateId)
    .orElseThrow(() -> {
      throw new UsernameNotFoundException("User not found");
    });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
    .description(candidate.getDescription())
    .username(candidate.getUsername())
    .email(candidate.getEmail())
    .name(candidate.getName())
    .id(candidate.getId())
    .build();

    return candidateDTO;
  }
}
