package com.br.jonasluis.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException() {
        super("Job não encontrado");
    }
}
