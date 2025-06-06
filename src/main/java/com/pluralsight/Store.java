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
    // Made a New Scanner for User Entry
    private static final String FILE_NAME = "products.csv";
    private static ArrayList<Product> inventory = new ArrayList<Product>();
    private static ArrayList<Product> cart = new ArrayList<Product>();
    private static double totalAmount = 0.0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Loads Inventory from CSV file
        System.out.println("Loading Application...\n");
        loadInventory(FILE_NAME, inventory);


        // While Loop to Keep Home Screen Open till user enters 3 to Exit the Application
        int homeScreenInput = 0;
        while (homeScreenInput != 3) {

            // Online Store Home Screen Display
            storeHomeScreenDisplay();

            // Home Screen User Selection
            homeScreenInput = scanner.nextInt();
            scanner.nextLine(); // scanner eater
            System.out.println("__________________________________________");

            switch (homeScreenInput) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    System.out.println("Choose an Option Below:");
                    System.out.println("C) Check Out Cart & Pay");
                    System.out.println("B) Go Back to Home Screen");
                    System.out.print("\nEnter Letter Here: ");
                    String displayCartInput = scanner.nextLine();
                    System.out.println("_________________________________________");

                    if (displayCartInput.equalsIgnoreCase("C")) {
                        switch (displayCartInput.toUpperCase()) {
                            case "C":
                                checkOut(cart, totalAmount);
                                break;
                            case "B":
                                System.out.println("Going to Home Screen...\n");
                                continue;
                        }
                    }
                case 3:
                    exitApplication();
                    break;
                default:
                    System.out.println("Invalid Option\n");
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

    public static void storeHomeScreenDisplay() {
        System.out.println("__________________________________________");
        System.out.println("Welcome to the Online Store!");
        System.out.println("__________________________________________");
        System.out.println("Choose an Option Below:");
        System.out.println("1. Show Products List");
        System.out.println("2. Show Cart & Check Out");
        System.out.println("3. Exit Online Store\n");
        System.out.print("Enter Number Here: ");
    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        /**
         *  This Method display the Array List of Products from the CSV File
         *  it does this by using a for each loop to print each product from the Inventory Array List.
         *  Then it gets a user input for the item they would like to add to the cart based on the Item ID.
         *  From there it adds that item to the User's Cart Array List
         */
        System.out.println("PRODUCT LIST & PRICES");
        System.out.println("____________________________________");
        for (Product product : inventory) {
            System.out.println(product);
        }
        System.out.println("_____________________________________");
        System.out.println("\nChoose an option by entering one of the corresponding letters:");
        System.out.println("A) Add a Product to Cart");
        System.out.println("B) Go Back");
        System.out.print("Enter Here: ");
        String input = scanner.nextLine();

        boolean wantsAnotherItem = true;

        while (wantsAnotherItem) {
            switch (input.toUpperCase()) {
                case "A":
                    System.out.print("\nSelect Items to Add to Cart By Entering appropriate Item ID: ");
                    String cartItem = scanner.nextLine();
                    Product product = findProductById(cartItem, inventory);

                    if (product != null) {
                        cart.add(product);
                        System.out.println("Added To Cart: " + product.getDescription() + "\n");
                    } else {
                        System.err.print("\nItem does not exist. Would you like to try to add Item to Cart again? (Y/N):  ");
                    }

                    System.out.print("Would you like to add another item to cart? (Y/N): ");
                    String addItemInput = scanner.nextLine();
                    switch (addItemInput.toUpperCase()) {
                        case "Y":
                            wantsAnotherItem = true;
                            continue;
                        case "N":
                            wantsAnotherItem = false;
                            System.out.println("Going Back to Home Screen...\n");
                            break;
                    }
                    break;

                case "B":
                    System.out.println("Going Back to Home Screen...\n");
                    wantsAnotherItem = false;
                    continue;

                default:
                    System.out.println("Invalid Option");
                    wantsAnotherItem = false;
            }
        }


    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        /**
         * This Method is used to display the items inside the User's Cart.
         * It also shows the user the Total Price for all the items they have in their cart.
         * If the User has nothing inside the Cart the application will tell the User that their cart is empty.
         */
        int cartCount = 0;
        for (Product product : cart) {
            System.out.println(product.getDescription() + " " + product.getPrice());
            totalAmount += product.getPrice();
            ++cartCount;
        }

        if (cartCount == 0) {
            System.out.println("Your Cart is Empty\n");
            int homeScreenInput = 3;
        }
        System.out.println("\nYour Total Price is $" + totalAmount + "\n");
    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        int cartCount = 0;
        for (Product product : cart) {
            totalAmount += product.getPrice();
            ++cartCount;

        }
        System.out.println("Total Sale: " + totalAmount);

        if (cartCount == 0) {
            System.out.println("Your Cart is Empty");
        }

        System.out.print("Please Enter Cash Amount to Pay: ");
        double cash = scanner.nextDouble();
        scanner.nextLine(); // scanner eater
        double change = cash - totalAmount;

        if (change <= 0) {
            System.err.println("Not Enough Money! Returning to Home Screen...");
        } else {
            for (Product product : cart) {
                System.out.println(product.getDescription() + " " + product.getPrice());
            }
            System.out.println("Your Change Total: $" + change);
            cart.clear();


        }

    }

    public static void exitApplication() {
        System.out.println("Thank you for shopping with us!");
    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        /**
         * This Method searches the Inventory Array List for a Product based on the User Entered ID
         * and returns the Product back to the  Main Method
         */
        for (Product product : inventory) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;


    }
}