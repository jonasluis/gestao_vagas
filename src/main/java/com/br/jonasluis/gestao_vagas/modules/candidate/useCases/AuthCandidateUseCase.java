package com.br.jonasluis.gestao_vagas.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.jonasluis.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.br.jonasluis.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.br.jonasluis.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(),
                candidate.getPassword());
        //Se mão for igual -> erro
        if(!passwordMatches){
            throw new AuthenticationException("Invalid credentials") {
            };
        }
        Algorithm algorithm  = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("jonas_company")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("candidate"))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder().access_token(token).build();
        return authCandidateResponse;
    }

}
