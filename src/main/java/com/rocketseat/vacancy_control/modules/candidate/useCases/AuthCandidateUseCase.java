package com.rocketseat.vacancy_control.modules.candidate.useCases;

import java.time.Instant;
import java.time.Duration;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rocketseat.vacancy_control.modules.candidate.repository.CandidateRepository;
import com.rocketseat.vacancy_control.modules.candidate.dto.AuthCandidateRequestDTO;
import com.rocketseat.vacancy_control.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretKey;
  
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
    var candidate = candidateRepository.findByUsername(authCandidateRequestDTO.username())
    .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

    var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException("Username/password incorrect");
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
    var token = JWT.create()
    .withIssuer("javagas")
    .withSubject(candidate.getId().toString())
    .withClaim("role", List.of("CANDIDATE"))
    .withExpiresAt(expiresIn)
    .sign(algorithm);

      return AuthCandidateResponseDTO.builder().access_token(token).expires_in(expiresIn.toEpochMilli()).build();
  }
}
