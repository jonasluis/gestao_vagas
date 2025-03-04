package com.br.jonasluis.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.jonasluis.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.br.jonasluis.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import com.br.jonasluis.gestao_vagas.modules.company.repositories.CompanyRepository;
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
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public  AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) {
        var company = this.companyRepository.findByUsername(authCompanyDTO.username()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        // Verificar a senha são iguais
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());
        //Se mão for igual -> erro
        if(!passwordMatches){
            throw new AuthenticationException("Invalid credentials") {
            };
        }
        // Se for -> Gerar token
        Algorithm algorithm  = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("jonas_company")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli()).build();
        return authCompanyResponseDTO;

    }

}
