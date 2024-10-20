package com.rocketseat.vacancy_control.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.vacancy_control.modules.company.entites.CompanyEntity;


public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
  
  Optional<CompanyEntity> findByUsername(String username);
}
