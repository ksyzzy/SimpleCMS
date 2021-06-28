package com.example.modelsservice.services;

import com.example.modelsservice.repositories.UserCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCompanyService {

    private UserCompanyRepository userCompanyRepository;

    public UserCompanyRepository getUserCompanyRepository() {
        return userCompanyRepository;
    }

    @Autowired
    public void setUserCompanyRepository(UserCompanyRepository userCompanyRepository) {
        this.userCompanyRepository = userCompanyRepository;
    }
}
