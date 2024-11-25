package com.rocketseat.vacancy_control.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthCandidateRequestDTO(
        @Schema(example = "daniel_soz")
        String username,

        @Schema(example = "admin@1234")
        String password
) { }
