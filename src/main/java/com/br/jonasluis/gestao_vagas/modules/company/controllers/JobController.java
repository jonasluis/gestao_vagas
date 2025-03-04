package com.br.jonasluis.gestao_vagas.modules.company.controllers;

import com.br.jonasluis.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.br.jonasluis.gestao_vagas.modules.company.entities.JobEntity;
import com.br.jonasluis.gestao_vagas.modules.company.repositories.JobRepository;
import com.br.jonasluis.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.benefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.description())
                .level(createJobDTO.level()).build();
        return this.createJobUseCase.execute(jobEntity);
    }
}
