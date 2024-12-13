package com.rocketseat.vacancy_control.models.candidate.usecases;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import javax.security.sasl.AuthenticationException;
import com.rocketseat.vacancy_control.modules.candidate.dto.AuthCandidateRequestDTO;
import com.rocketseat.vacancy_control.modules.candidate.dto.AuthCandidateResponseDTO;
import com.rocketseat.vacancy_control.modules.candidate.entity.CandidateEntity;
import com.rocketseat.vacancy_control.modules.candidate.repository.CandidateRepository;
import com.rocketseat.vacancy_control.modules.candidate.useCases.AuthCandidateUseCase;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthCandidateTest {

    @InjectMocks
    private AuthCandidateUseCase authCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should not be able to auth candidate with wrong username")
    public void should_not_be_able_to_auth_candidate_with_wrong_username() {
        // Build request
        AuthCandidateRequestDTO authRequest = new AuthCandidateRequestDTO("wrong_username", "wrong_password");

        assertThatThrownBy(() -> authCandidateUseCase.execute(authRequest))
            .isInstanceOf(UsernameNotFoundException.class)
            .hasMessage("Username/password incorrect");
    }

    @Test 
    @DisplayName("Should not be able to auth candidate with wrong password")
    public void should_not_be_able_to_auth_candidate_with_wrong_password() {
        // Build request
        AuthCandidateRequestDTO authRequest = new AuthCandidateRequestDTO("admin", "wrong_password");

        // Check if candidate exists
        when(candidateRepository.findByUsername("admin")).thenReturn(Optional.of(new CandidateEntity()));
        // Check if password is correct
        when(this.passwordEncoder.matches(authRequest.password(), "password")).thenReturn(false);

        assertThatThrownBy(() -> authCandidateUseCase.execute(authRequest))
            .isInstanceOf(AuthenticationException.class)
            .hasMessage("Username/password incorrect");
    }
}
