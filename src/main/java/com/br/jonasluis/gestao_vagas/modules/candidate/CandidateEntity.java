package com.br.jonasluis.gestao_vagas.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;
    @NotBlank
    @Pattern(regexp = "^\\s+", message = "O campo [username] não deve conter espaço")
    private String username;
    @Email(message = "O campo [email] deve conter um email válido")
    private String email;
    @Length(min = 8, max = 20, message = "A senha deve conter entre (8) e (20) caracteres")
    private String password;
    private String description;
    private String curriculum;

}
