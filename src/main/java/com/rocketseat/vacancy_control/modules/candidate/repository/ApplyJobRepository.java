package com.rocketseat.vacancy_control.modules.candidate.repository;


import com.rocketseat.vacancy_control.modules.candidate.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}
