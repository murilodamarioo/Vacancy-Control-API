package com.rocketseat.vacancy_control.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

  @Schema(example = "soft_inno")
  private String username;

  @Schema(example = "Admin@1234")
  private String password;

}
