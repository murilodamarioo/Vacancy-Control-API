package com.rocketseat.vacancy_control.modules.company.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.vacancy_control.modules.company.dto.CreateJobDTO;
import com.rocketseat.vacancy_control.modules.company.entites.JobEntity;
import com.rocketseat.vacancy_control.modules.company.useCases.CreateJobUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("company/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;
  
  @PostMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    var jobEntity = JobEntity.builder()
    .benefits(createJobDTO.getBenefits())
    .companyId(UUID.fromString(companyId.toString()))
    .description(createJobDTO.getDescription())
    .level(createJobDTO.getLevel())
    .build();
    
    var response = this.createJobUseCase.execute(jobEntity);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
