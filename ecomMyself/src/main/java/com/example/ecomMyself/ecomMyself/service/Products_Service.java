package com.example.ecomMyself.ecomMyself.service;

import com.example.ecomMyself.ecomMyself.model.Product;
import com.example.ecomMyself.ecomMyself.repository.Product_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Products_Service {
    @Autowired
    private Product_Repo product_repo;

    public List<Product> getAll()
    {
        List<Product> products=product_repo.findAll();
        return products;
    }

    public List<Product> getMenAll() {
        List<Product> products=product_repo.findAllByGender("Male");
        return products;
    }
    public List<Product> getWomenAll() {
        List<Product> products=product_repo.findAllByGender("Female");
        return products;
    }

    public Product productById(int id) {
        Optional<Product> p=product_repo.findById(id);
        if(p.isEmpty())
            throw new RuntimeException("");
        return p.get();
    }
}
