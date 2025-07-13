package com.br.jonasluis.gestao_vagas.modules.candidate.entity;

import com.br.jonasluis.gestao_vagas.modules.company.entities.JobEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "apply_job")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_job", insertable = false, updatable = false)
    private JobEntity jobEntity;

    @ManyToOne
    @JoinColumn(name = "id_candidate", insertable = false, updatable = false)
    private CandidateEntity candidateEntity;

    @Column(name = "id_job")
    private UUID jobId;
    @Column(name = "id_candidate")
    private UUID candidateId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
