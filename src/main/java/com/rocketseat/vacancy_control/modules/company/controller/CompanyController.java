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

import com.rocketseat.vacancy_control.modules.company.entites.CompanyEntity;
import com.rocketseat.vacancy_control.modules.company.useCases.CreateCompanyUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
@Tag(name = "Company", description = "Company information")
public class CompanyController {
  
  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  @PostMapping("/")
  @Operation(summary = "Company authentication")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
                  @Content(schema = @Schema(implementation = CompanyEntity.class))
          }),
          @ApiResponse(responseCode = "400", description = "User already exists")
  })
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
    try {
      var response = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
