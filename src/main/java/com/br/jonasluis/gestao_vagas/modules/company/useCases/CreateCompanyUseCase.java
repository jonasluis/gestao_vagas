package com.br.jonasluis.gestao_vagas.modules.company.useCases;

import com.br.jonasluis.gestao_vagas.exceptions.UserFoundException;
import com.br.jonasluis.gestao_vagas.modules.company.entities.CompanyEntity;
import com.br.jonasluis.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository.findByUsernameOrEmail(companyEntity.
                getUsername(), companyEntity.getEmail()).ifPresent((user) -> {
                    throw new UserFoundException();
        });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
