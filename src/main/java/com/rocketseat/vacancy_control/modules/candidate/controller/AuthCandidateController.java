package com.rocketseat.vacancy_control.modules.candidate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.vacancy_control.modules.candidate.dto.AuthCandidateRequestDTO;
import com.rocketseat.vacancy_control.modules.candidate.useCases.AuthCandidateUseCase;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Candidate information")
public class AuthCandidateController {
  
  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/auth")
  @Operation(summary = "Candidate authentication")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
                  @Content(schema = @Schema(implementation = AuthCandidateRequestDTO.class))
          }),
          @ApiResponse(responseCode = "401", description = "Username/password incorrect")
  })
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
    try {
      var response = this.authCandidateUseCase.execute(authCandidateRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
