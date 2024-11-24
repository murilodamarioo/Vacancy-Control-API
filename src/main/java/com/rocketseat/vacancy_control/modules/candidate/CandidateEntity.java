package com.rocketseat.vacancy_control.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidates")
public class CandidateEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Daniel de Souza", requiredMode = Schema.RequiredMode.REQUIRED)
  private String name;

  @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
  @Schema(example = "daniel_soz", requiredMode = Schema.RequiredMode.REQUIRED)
  private String username;

  @Email(message = "O campo [email] deve conter um e-mail válido")
  @Schema(example = "daniel@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
  private String email;
  
  @Length(min = 6, max = 100, message = "O campo [password] deve conter entre 6 e 100 caracteres")
  @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
  private String password;

  @Schema(example = "java Developer", requiredMode = Schema.RequiredMode.REQUIRED)
  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
