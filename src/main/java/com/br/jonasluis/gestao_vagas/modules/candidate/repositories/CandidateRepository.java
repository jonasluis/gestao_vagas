package com.br.jonasluis.gestao_vagas.modules.candidate.repositories;

import com.br.jonasluis.gestao_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository <CandidateEntity, UUID> {
}
