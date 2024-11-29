package com.rocketseat.vacancy_control.models.candidate.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.rocketseat.vacancy_control.exceptions.JobNotFoundException;
import com.rocketseat.vacancy_control.exceptions.UserNotFoundException;
import com.rocketseat.vacancy_control.modules.candidate.CandidateEntity;
import com.rocketseat.vacancy_control.modules.candidate.CandidateRepository;
import com.rocketseat.vacancy_control.modules.candidate.useCases.ApplyJobCandidateUseCase;
import com.rocketseat.vacancy_control.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

}
