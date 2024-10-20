package com.rocketseat.vacancy_control.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.vacancy_control.modules.company.entites.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
