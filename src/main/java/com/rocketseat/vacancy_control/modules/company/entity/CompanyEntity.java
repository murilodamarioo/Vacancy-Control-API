package com.rocketseat.vacancy_control.modules.company.entity;

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

@Entity(name = "companies")
@Data
public class CompanyEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Soft&Inno", requiredMode = Schema.RequiredMode.REQUIRED)
  private String name;

  @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
  @Schema(example = "soft_inno", requiredMode = Schema.RequiredMode.REQUIRED)
  private String username;

  @Email(message = "O campo [email] deve conter um e-mail válido")
  @Schema(example = "soft@email.com")
  private String email;
  
  @Length(min = 6, max = 100, message = "O campo [password] deve conter entre 6 e 100 caracteres")
  @Schema(example = "Admin@1234", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 6, maxLength = 100)
  private String password;
  private String website;

  @Schema(example = "Description about company", requiredMode = Schema.RequiredMode.REQUIRED)
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
