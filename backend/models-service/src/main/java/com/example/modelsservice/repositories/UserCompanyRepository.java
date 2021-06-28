package com.example.modelsservice.repositories;

import com.example.modelsservice.models.User_Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompanyRepository extends JpaRepository<User_Company, Long> {
}
