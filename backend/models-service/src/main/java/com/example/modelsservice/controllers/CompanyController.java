package com.example.modelsservice.controllers;

import com.example.modelsservice.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

}
