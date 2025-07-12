package com.br.jonasluis.gestao_vagas.modules.candidate.controllers;

import com.br.jonasluis.gestao_vagas.exceptions.UserFoundException;
import com.br.jonasluis.gestao_vagas.modules.candidate.CandidateEntity;
import com.br.jonasluis.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.br.jonasluis.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.br.jonasluis.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.br.jonasluis.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.br.jonasluis.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import com.br.jonasluis.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informaçoes do candidato")
public class CandidateController {

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/")
    @Operation(
            summary = "Cadastro do candidato", description = "Esssa função é responsavel por cadastrar o candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content( schema = @Schema(implementation = CandidateEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usúario já existe")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
            summary = "Perfil do candidato", description = "Esssa função é responsavel por buscar as informações do perfil do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content( schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User Not Found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.profileCandidateUseCase
                    .execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
            summary = "Listagem de vagas disponiveis para o candidato", description = "Esssa função é responsavel por listar todas as vagas disponiveis, baseada nos filtros")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))
                    )
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }
}
