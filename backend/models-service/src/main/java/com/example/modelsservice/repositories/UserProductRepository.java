package com.example.modelsservice.repositories;

import com.example.modelsservice.models.User_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepository extends JpaRepository<User_Product, Long> {
}
