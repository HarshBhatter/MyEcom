package com.example.ecomMyself.ecomMyself.repository;

import com.example.ecomMyself.ecomMyself.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_Repo extends JpaRepository<Product,Integer> {
    List<Product> findAllByGender(String gender);
}
