package com.example.ecomMyself.ecomMyself.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
//    @GeneratedValue : this creates an extra table in myswl for counter to stop it we use :-
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String Type;
    private String fit;
    private double price;
    private String description;
    private String gender;
    @OneToMany(mappedBy = "product")
    private List<Product_colors> color;
    @Lob
    private Byte[] picture;

    public Product() {
        this.name="-";
        this.Type="-";
        this.fit="-";
        this.price = 0;
        this.description="-";
        gender="Bi";
    }

    public Product(int id, String name, String type, String fit, List<Integer> size, int count, double price, String description,List<Product_colors> color,Byte[] picture) {
        this.id = id;
        this.name = name;
        this.Type = type;
        this.fit = fit;
        this.price = price;
        this.description = description;
        this.picture=picture;
        this.color=color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product_colors> getColor() {
        return color;
    }

    public void setColor(List<Product_colors> color) {
        this.color = color;
    }
}
