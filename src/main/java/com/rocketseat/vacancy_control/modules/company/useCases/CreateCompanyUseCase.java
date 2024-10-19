package com.rocketseat.vacancy_control.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.vacancy_control.exceptions.UserFoundException;
import com.rocketseat.vacancy_control.modules.company.entites.CompanyEntity;
import com.rocketseat.vacancy_control.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository
    .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
    .ifPresent((user) -> {
      throw new UserFoundException();
    });
    return this.companyRepository.save(companyEntity);
  }
}
