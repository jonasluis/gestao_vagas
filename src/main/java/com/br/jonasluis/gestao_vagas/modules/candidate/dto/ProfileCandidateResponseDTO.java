package com.br.jonasluis.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;


@Builder
public record ProfileCandidateResponseDTO(
        @Schema(example = "Desenvolvedor Java")
        String description,
        @Schema(example = "jonasluis")
        String username,
        @Schema(example = "jonasluis@gmail.com")
        String email,
        UUID id,
        @Schema(example = "Jonas Luis")
        String name) {
}
