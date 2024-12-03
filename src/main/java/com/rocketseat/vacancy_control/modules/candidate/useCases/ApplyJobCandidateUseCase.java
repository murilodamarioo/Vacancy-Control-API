package com.rocketseat.vacancy_control.modules.candidate.useCases;

import com.rocketseat.vacancy_control.exceptions.JobNotFoundException;
import com.rocketseat.vacancy_control.exceptions.UserNotFoundException;
import com.rocketseat.vacancy_control.modules.candidate.entity.ApplyJobEntity;
import com.rocketseat.vacancy_control.modules.candidate.repository.ApplyJobRepository;
import com.rocketseat.vacancy_control.modules.candidate.repository.CandidateRepository;
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

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        this.candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);

        this.jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        
        return applyJob;
    }
}
