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


}
