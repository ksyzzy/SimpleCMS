package com.example.modelsservice.services;

import com.example.modelsservice.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyRepository getCompanyRepository() {
        return companyRepository;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}
