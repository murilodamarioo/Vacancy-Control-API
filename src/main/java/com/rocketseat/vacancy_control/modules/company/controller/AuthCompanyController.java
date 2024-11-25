package com.rocketseat.vacancy_control.modules.company.controller;

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

import com.rocketseat.vacancy_control.modules.company.dto.AuthCompanyDTO;
import com.rocketseat.vacancy_control.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/company")
@Tag(name = "Company", description = "Company information")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;
  
  @PostMapping("/auth")
  @Operation(summary = "Company authentication")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
                  @Content(schema = @Schema(implementation = AuthCompanyDTO.class))
          }),
          @ApiResponse(responseCode = "401", description = "Username/password incorrect")
  })
  public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
    try {
      var response = this.authCompanyUseCase.execute(authCompanyDTO);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
