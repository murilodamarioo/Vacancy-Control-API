package com.rocketseat.vacancy_control.modules.candidate.controller;

import java.util.List;
import java.util.UUID;

import com.rocketseat.vacancy_control.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.rocketseat.vacancy_control.modules.candidate.useCases.ApplyJobCandidateUseCase;
import com.rocketseat.vacancy_control.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.rocketseat.vacancy_control.modules.company.entity.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rocketseat.vacancy_control.modules.candidate.entity.CandidateEntity;
import com.rocketseat.vacancy_control.modules.candidate.useCases.CreateCandidateUseCase;
import com.rocketseat.vacancy_control.modules.candidate.useCases.ProfileCadidateUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("candidate")
@Tag(name = "Candidate", description = "Candidate information")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCadidateUseCase profileCadidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @Autowired
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;
  
  @PostMapping("/")
  @Operation(summary = "Candidate register")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
                  @Content(schema = @Schema(implementation = CandidateEntity.class))
          }),
          @ApiResponse(responseCode = "400", description = "User already exists")
  })
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
        var response = this.createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch(Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Candidate profile")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
                  @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
          }),
          @ApiResponse(responseCode = "400", description = "User not found")
  })
  @SecurityRequirement(name = "jwt_auth")
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
  @Operation(summary = "List of vacancies available to the candidate")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
                  @Content(
                          array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))
                  )
          })
  })
  @SecurityRequirement(name = "jwt_auth")
  public List<JobEntity> findJobByFilter(@RequestParam String filter) {
    return this.listAllJobsByFilterUseCase.execute(filter);
  }

  @PostMapping("/job/apply")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Candidate subscriptions to a vacancy")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob) {
    var idCandidate = request.getAttribute("candidate_id");

    var response = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), idJob);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
