package com.example.ecomMyself.ecomMyself.repository;

import com.example.ecomMyself.ecomMyself.model.Product;
import com.example.ecomMyself.ecomMyself.model.Product_colors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Product_colors_repo extends JpaRepository<Product_colors,Integer> {

    Product_colors findByProduct(Product product);
}
