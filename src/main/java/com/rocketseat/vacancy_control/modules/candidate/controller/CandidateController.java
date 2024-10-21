package com.rocketseat.vacancy_control.modules.candidate.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.vacancy_control.modules.candidate.CandidateEntity;
import com.rocketseat.vacancy_control.modules.candidate.useCases.CreateCandidateUseCase;
import com.rocketseat.vacancy_control.modules.candidate.useCases.ProfileCadidateUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCadidateUseCase profileCadidateUseCase;
  
  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
        var response = this.createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.status(201).body(response);
    } catch(Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


  @GetMapping("/")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var candidateId = request.getAttribute("candidate_id");

    try {
      var profile = this.profileCadidateUseCase.execute(UUID.fromString(candidateId.toString()));
      return ResponseEntity.status(HttpStatus.OK).body(profile);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
