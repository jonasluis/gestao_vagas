package com.br.jonasluis.gestao_vagas.module.company.controllers;

import com.br.jonasluis.gestao_vagas.exceptions.CompanyNotFoundException;
import com.br.jonasluis.gestao_vagas.module.utils.TestUtils;
import com.br.jonasluis.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.br.jonasluis.gestao_vagas.modules.company.entities.CompanyEntity;
import com.br.jonasluis.gestao_vagas.modules.company.repositories.CompanyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        System.out.println("Iniciando o teste");

        var company = CompanyEntity.builder()
                .description("COMPANY_TEST")
                .email("test@gmail.com")
                .password("1234567890")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME").build();

        company = companyRepository.saveAndFlush(company);

        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectMapper(createJobDTO))
                        .header("Authorization", TestUtils.generateToken(company.getId(), "JONAS_COMPANY@123")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println("Requisição concluída com sucesso");
    }

    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();


        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectMapper(createJobDTO))
                        .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "JONAS_COMPANY@123")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
