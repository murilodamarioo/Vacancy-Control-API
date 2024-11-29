package com.rocketseat.vacancy_control.modules.candidate.useCases;

import com.rocketseat.vacancy_control.exceptions.JobNotFoundException;
import com.rocketseat.vacancy_control.exceptions.UserNotFoundException;
import com.rocketseat.vacancy_control.modules.candidate.CandidateRepository;
import com.rocketseat.vacancy_control.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute(UUID idCandidate, UUID idJob) {
        this.candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);

        this.jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);

    }
}
