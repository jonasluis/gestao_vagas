package com.br.jonasluis.gestao_vagas.modules.candidate.useCases;

import com.br.jonasluis.gestao_vagas.exceptions.JobNotFoundException;
import com.br.jonasluis.gestao_vagas.exceptions.UserNotFoundException;
import com.br.jonasluis.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import com.br.jonasluis.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.br.jonasluis.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.br.jonasluis.gestao_vagas.modules.company.repositories.JobRepository;
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
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(() ->{
                    throw new UserNotFoundException();
                        });

        this.jobRepository.findById(idJob)
                .orElseThrow(() -> {
                    throw  new JobNotFoundException();
                });
        var applyJob = ApplyJobEntity.builder()
                .jobId(idJob)
                .candidateId(idCandidate).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;

    }
}
