package com.br.jonasluis.gestao_vagas.modules.candidate.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Jonas Luis", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;
    @NotBlank
    @Pattern(regexp = "^[^\\s]+$", message = "O campo [username] não deve conter espaços")
    @Schema(example = "jonasluis", requiredMode = Schema.RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;
    @Schema(example = "jonasluis@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "E-mail do candidato")
    @Email(message = "O campo [email] deve conter um email válido")
    private String email;
    @Length(min = 8, max = 30, message = "A senha deve conter entre (8) e (20) caracteres")
    @Schema(example = "12345678", minLength = 8, maxLength = 30, requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;
    @Schema(example = "Desenvolvedor Java", requiredMode = Schema.RequiredMode.REQUIRED, description = "Breve descrição do candidato")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
