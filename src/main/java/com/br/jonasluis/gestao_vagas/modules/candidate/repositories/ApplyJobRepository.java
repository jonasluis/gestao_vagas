package com.br.jonasluis.gestao_vagas.modules.candidate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobRepository, UUID> {
}
