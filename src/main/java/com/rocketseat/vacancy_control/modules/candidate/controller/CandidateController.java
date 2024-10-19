package com.rocketseat.vacancy_control.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.vacancy_control.modules.candidate.CandidateEntity;
import com.rocketseat.vacancy_control.modules.candidate.useCases.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;
  
  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
        var response = this.createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.ok().body(response);
    } catch(Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
