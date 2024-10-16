package com.rocketseat.vacancy_control.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
  
  private UUID id;
  private String name;
  @Pattern(regexp = "^(?!\\s*$).+", message = "username não deve conter espaço")
  private String usename;

  @Email(message = "Deve ser informado um e-mail válido")
  private String email;
  
  @Length(min = 6, max = 100)
  private String password;
  
  private String description;
  private String curriculum;


}
