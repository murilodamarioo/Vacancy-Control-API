package com.rocketseat.vacancy_control.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

  @Schema(example = "Vacancy for Junior Developer in Javascript", requiredMode = Schema.RequiredMode.REQUIRED)
  private String description;

  @Schema(example = "Health insurance, Renumbered vacation", requiredMode = Schema.RequiredMode.REQUIRED)
  private String benefits;

  @Schema(example = "Junior", requiredMode = Schema.RequiredMode.REQUIRED)
  private String level;

}
