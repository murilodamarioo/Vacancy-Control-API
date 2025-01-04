package com.rocketseat.vacancy_control.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocketseat.vacancy_control.modules.candidate.repository.CandidateRepository;
import com.rocketseat.vacancy_control.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCadidateUseCase {
  
  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID candidateId) {
    var candidate = this.candidateRepository.findById(candidateId)
    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      return ProfileCandidateResponseDTO.builder()
      .description(candidate.getDescription())
      .username(candidate.getUsername())
      .email(candidate.getEmail())
      .name(candidate.getName())
      .id(candidate.getId())
      .build();
  }
}
