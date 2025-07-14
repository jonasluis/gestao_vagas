package com.br.jonasluis.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
@Builder
public record CreateJobDTO(
        @Schema(example = "Vaga para pessoa desenvolvedora Junior")
        String description,
        @Schema(example = "GYMPASS, Plano de Saúde, VT, VA e VR")
        String benefits,
        @Schema(example = "JUNIOR")
        String level
) {
}
