package com.br.jonasluis.gestao_vagas.modules.candidate.controllers;

import com.br.jonasluis.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.br.jonasluis.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import com.br.jonasluis.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.br.jonasluis.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/candidate")
    public ResponseEntity<Object> auth(@Valid @RequestBody AuthCandidateRequestDTO authCandidateRequestDTO){
         try {
             var result = this.authCandidateUseCase.execute(authCandidateRequestDTO);
             return ResponseEntity.ok().body(result);
         }catch (Exception e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

         }
    }

}
