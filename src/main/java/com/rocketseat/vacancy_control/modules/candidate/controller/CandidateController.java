package com.rocketseat.vacancy_control.modules.candidate.controller;

import java.util.List;
import java.util.UUID;

import com.rocketseat.vacancy_control.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.rocketseat.vacancy_control.modules.company.entites.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;
  
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
  @PreAuthorize("hasRole('CANDIDATE')")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var candidateId = request.getAttribute("candidate_id");

    try {
      var profile = this.profileCadidateUseCase.execute(UUID.fromString(candidateId.toString()));
      return ResponseEntity.status(HttpStatus.OK).body(profile);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/job")
  @PreAuthorize("hasRole('CANDIDATE')")
  public List<JobEntity> findJobByFilter(@RequestParam String filter) {
    return this.listAllJobsByFilterUseCase.execute(filter);
  }
}
