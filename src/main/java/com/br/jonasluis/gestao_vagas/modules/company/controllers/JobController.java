package com.br.jonasluis.gestao_vagas.modules.company.controllers;

import com.br.jonasluis.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.br.jonasluis.gestao_vagas.modules.company.entities.JobEntity;
import com.br.jonasluis.gestao_vagas.modules.company.repositories.JobRepository;
import com.br.jonasluis.gestao_vagas.modules.company.useCases.CreateJobUseCase;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    public CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informaçoes das vagas")
    @Operation(
            summary = "Cadastro de vagas", description = "Esssa função é responsavel por cadastrar as vagas disponiveis dentro da empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        var companyId = request.getAttribute("company_id");

        try {

        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.benefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.description())
                .level(createJobDTO.level()).build();
        var result = this.createJobUseCase.execute(jobEntity);
           return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
