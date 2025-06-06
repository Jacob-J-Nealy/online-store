package com.pluralsight;

import java.lang.reflect.Constructor;

public class Product {

    // Attributes
    private String id;
    private String description;
    private double price;

    // Constructor (Inventory)
    public Product(String id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    //

    @Override
    public String toString() {
      StringBuilder productBuilder = new StringBuilder();
      productBuilder.append(id);
      productBuilder.append("|");
      productBuilder.append(description);
      productBuilder.append("|");
      productBuilder.append(price);
      return String.format("%s |%s|%.2f", id, description, price);
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
