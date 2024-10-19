package com.rocketseat.vacancy_control.modules.candidate.controller;

import com.rocketseat.vacancy_control.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.vacancy_control.modules.candidate.CandidateEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("candidate")
public class CandidateController {

  @Autowired
  private CandidateRepository candidateRepository;
  
  @PostMapping("/")
  public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
    return this.candidateRepository.save(candidateEntity);
  }
}
