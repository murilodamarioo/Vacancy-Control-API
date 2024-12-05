package com.rocketseat.vacancy_control.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.vacancy_control.exceptions.CompanyNotFoundException;
import com.rocketseat.vacancy_control.modules.company.entity.JobEntity;
import com.rocketseat.vacancy_control.modules.company.repositories.CompanyRepository;
import com.rocketseat.vacancy_control.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CompanyRepository companyRepository;
  
  public JobEntity execute(JobEntity jobEntity) {
    this.companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
      throw new CompanyNotFoundException();
    });

    return this.jobRepository.save(jobEntity);
  }
}
