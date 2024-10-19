package com.rocketseat.vacancy_control.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.vacancy_control.exceptions.UserFoundException;
import com.rocketseat.vacancy_control.modules.candidate.CandidateEntity;
import com.rocketseat.vacancy_control.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
    .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
    .ifPresent((user) -> {
      throw new UserFoundException();
    });
    return this.candidateRepository.save(candidateEntity);
  }
}
