package com.br.jonasluis.gestao_vagas.modules.candidate.dto;

import lombok.Builder;

@Builder
public record AuthCandidateResponseDTO(String access_token) {
}
