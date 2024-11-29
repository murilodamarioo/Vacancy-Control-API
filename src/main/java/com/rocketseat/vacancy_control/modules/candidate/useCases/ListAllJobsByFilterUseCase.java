package com.rocketseat.vacancy_control.modules.candidate.useCases;

import com.rocketseat.vacancy_control.modules.company.entity.JobEntity;
import com.rocketseat.vacancy_control.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository repository;

    public List<JobEntity> execute(String filter) {
        return this.repository.findByDescriptionContainingIgnoreCase(filter);
    }
}
