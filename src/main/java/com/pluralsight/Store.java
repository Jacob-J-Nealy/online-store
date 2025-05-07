package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    // Set CSV File to Read (change the file name if you want to use this app for a different list (Learned from Capstone 1)
    // Initialized Array List for Inventory
    // Initialized Array List for User's Cart
    // Declared Variable for Total  Amount
    private static final String FILE_NAME = "products.csv";
    private static ArrayList<Product> inventory = new ArrayList<Product>();
    private static ArrayList<Product> cart = new ArrayList<Product>();
    private static double totalAmount = 0.0;

    public static void main(String[] args) {

        //Loads Inventory from CSV file
        System.out.println("Loading Application...\n");
        loadInventory(FILE_NAME, inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {

            // Online Store Home Screen Display
            System.out.println("Welcome to the Online Store!\n");
            System.out.println("Choose an Option Below!");
            System.out.println("__________________________________________");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit\n");
            System.out.print("Enter Number Here: ");

            // User Selection
            choice = scanner.nextInt();
            scanner.nextLine(); // scanner eater
            System.out.println("__________________________________________");




            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    //displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    System.out.println("Closing Application...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        /**
         * This Method is used to Load the Inventory from the CSV File.
         * It also adds the Products from the CSV to an Array List by splitting the attributes of the products into
         * different parts and setting those parts to the specific attributes.
         */
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String id = parts[0];
                String description = parts[1];
                double price = Double.parseDouble(parts[2]);
                inventory.add(new Product(id, description, price));
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.err.println("Error; Couldn't Load Files...");
        }
    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        // This method should display a list of products from the inventory,
        // and prompt the user to add items to their cart. The method should
        // prompt the user to enter the ID of the product they want to add to
        // their cart. The method should
        // add the selected product to the cart ArrayList.

        for (Product product : inventory);
            System.out.println(inventory);
        }
    }

//    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
//        // This method should display the items in the cart ArrayList, along
//        // with the total cost of all items in the cart. The method should
//        // prompt the user to remove items from their cart by entering the ID
//        // of the product they want to remove. The method should update the cart ArrayList and totalAmount
//        // variable accordingly.
//    }

//    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
//        // This method should calculate the total cost of all items in the cart,
//        // and display a summary of the purchase to the user. The method should
//        // prompt the user to confirm the purchase, and calculate change and clear the cart
//        // if they confirm.
//    }

    // public static Product findProductById(String id, ArrayList<Product> inventory) {
        // This method should search the inventory ArrayList for a product with
        // the specified ID, and return the corresponding com.pluralsight.Product object. If
        // no product with the specified ID is found, the method should return
        // null.

