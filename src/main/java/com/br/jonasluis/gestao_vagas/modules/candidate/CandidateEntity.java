package com.br.jonasluis.gestao_vagas.modules.candidate;

import lombok.*;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String description;
    private String curriculum;

}
