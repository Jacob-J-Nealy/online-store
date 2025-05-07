package com.pluralsight;

import java.lang.reflect.Constructor;

public class Product {

    // Attributes
    private String id;
    private String description;
    private double price;

    // Constructor
    public Product(String id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
      StringBuilder productBuilder = new StringBuilder();

    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
