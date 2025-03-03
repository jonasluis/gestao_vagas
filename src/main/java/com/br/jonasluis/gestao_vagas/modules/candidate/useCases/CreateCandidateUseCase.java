package com.br.jonasluis.gestao_vagas.modules.candidate.useCases;

import com.br.jonasluis.gestao_vagas.exceptions.UserFoundException;
import com.br.jonasluis.gestao_vagas.modules.candidate.CandidateEntity;
import com.br.jonasluis.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity){

        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
                candidateEntity.getEmail()).ifPresent((user) ->{
            throw new UserFoundException();
        });

        return this.candidateRepository.save(candidateEntity);
    }
}
