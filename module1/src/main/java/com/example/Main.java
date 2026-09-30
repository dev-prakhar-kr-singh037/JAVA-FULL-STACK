
package com.example;

import java.util.Scanner;

public class Main 
{
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final InventoryManager MANAGER = new InventoryManager();

    public static void main(String[] args) 
    {
        System.out.println("==========================================");
        System.out.println("     INVENTORY MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        try (SCANNER) {
            boolean running = true;

            while (running) {
                displayMainMenu();
                int choice = readInt("Enter your choice: ");
                switch (choice) {
                    case 1 -> productMenu();
                    case 2 -> categoryMenu();
                    case 3 -> stockMenu();
                    case 4 -> MANAGER.displayLowStock(5);
                    case 5 -> {
                        System.out.println("\nThank you for using");
                        System.out.println("Inventory Management System.");
                        running = false;
                    }
                    default -> System.out.println("\nInvalid choice.");
                }
            }
        }
    }

    // =========================================================
    // MAIN MENU
    // =========================================================

    private static void displayMainMenu() 
    {
        System.out.println("\n==========================================");
        System.out.println("              MAIN MENU");
        System.out.println("==========================================");

        System.out.println("1. Product Management");
        System.out.println("2. Category Management");
        System.out.println("3. Stock Management");
        System.out.println("4. Low Stock Report");
        System.out.println("5. Exit");

        System.out.println("==========================================");
    }

    // =========================================================
    // PRODUCT MENU
    // =========================================================

    private static void productMenu() 
    {
        boolean back = false;

        while (!back) 
        {
            System.out.println("\n------------------------------------------");
            System.out.println("          PRODUCT MANAGEMENT");
            System.out.println("------------------------------------------");

            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> MANAGER.displayProducts();
                case 3 -> searchProduct();
                case 4 -> updateProduct();
                case 5 -> deleteProduct();
                case 6 -> System.out.println("ThankU See you again");
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================================
    // ADD PRODUCT
    // =========================================================

    private static void addProduct() 
    {
        System.out.println("\n----------- ADD PRODUCT -----------");

        int id = MANAGER.getNextProductId();

        System.out.println("Product ID: " + id);

        String name = readString("Enter product name: ");

        String category = readString("Enter category: ");

        double price = readDouble("Enter price: ");

        int stock = readInt("Enter initial stock: ");

        if (price < 0 || stock < 0) 
        {
            System.out.println("Price and stock cannot be negative.");
            return;
        }
        Product product = new Product(id, name, category, price, stock);

        if (MANAGER.addProduct(product)) 
        {
            System.out.println("Product added successfully.");
        } 
        else 
        {
            System.out.println("Unable to add product.");
        }
    }

    // =========================================================
    // SEARCH PRODUCT
    // =========================================================

    private static void searchProduct() 
    {
        System.out.println("\n----------- SEARCH PRODUCT -----------");
        String name = readString("Enter product name: ");
        MANAGER.searchProduct(name);
    }

    // =========================================================
    // UPDATE PRODUCT
    // =========================================================

    private static void updateProduct() 
    {
        System.out.println("\n----------- UPDATE PRODUCT -----------");
        int id = readInt("Enter product ID: ");
        Product product = MANAGER.findProductById(id);
        if (product == null) 
        {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("\nCurrent Product:");
        System.out.println(product);
        String name = readString("Enter new name: ");
        String category = readString("Enter new category: ");
        double price = readDouble("Enter new price: ");

        if (price < 0) 
        {
            System.out.println("Price cannot be negative.");
            return;
        }

        if (MANAGER.updateProduct(id, name, category, price)) 
        {
            System.out.println("Product updated successfully.");
        } 
        else 
        {
            System.out.println("Unable to update product.");
        }
    }

    // =========================================================
    // DELETE PRODUCT
    // =========================================================

    private static void deleteProduct() 
    {
        System.out.println("\n----------- DELETE PRODUCT -----------");
        int id = readInt("Enter product ID: ");
        Product product = MANAGER.findProductById(id);

        if (product == null) 
        {
            System.out.println("Product not found.");
            return;
        }

        System.out.println(product);

        String confirmation =
                readString(
                        "Are you sure? (yes/no): ");

        if (confirmation.equalsIgnoreCase("yes")) {

            if (MANAGER.deleteProduct(id)) {

                System.out.println(
                        "Product deleted successfully.");

            } else {

                System.out.println(
                        "Unable to delete product.");
            }

        } else {

            System.out.println("Delete operation cancelled.");
        }
    }

    // =========================================================
    // CATEGORY MENU
    // =========================================================

    private static void categoryMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n------------------------------------------");
            System.out.println("          CATEGORY MANAGEMENT");
            System.out.println("------------------------------------------");

            System.out.println("1. Add Category");
            System.out.println("2. View Categories");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Back");

            int choice =
                    readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addCategory();
                case 2 -> MANAGER.displayCategories();
                case 3 -> updateCategory();
                case 4 -> deleteCategory();
                case 5 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================================
    // ADD CATEGORY
    // =========================================================

    private static void addCategory() {

        System.out.println("\n----------- ADD CATEGORY -----------");

        int id =
                MANAGER.getNextCategoryId();

        System.out.println("Category ID: " + id);

        String name =
                readString("Enter category name: ");

        Category category =
                new Category(id, name);

        if (MANAGER.addCategory(category)) {

            System.out.println(
                    "Category added successfully.");

        } else {

            System.out.println(
                    "Category already exists.");
        }
    }

    // =========================================================
    // UPDATE CATEGORY
    // =========================================================

    private static void updateCategory() {

        System.out.println("\n----------- UPDATE CATEGORY -----------");

        int id =
                readInt("Enter category ID: ");

        Category category =
                MANAGER.findCategoryById(id);

        if (category == null) {

            System.out.println(
                    "Category not found.");

            return;
        }

        System.out.println(
                "Current category: "
                        + category.getName());

        String name =
                readString("Enter new category name: ");

        if (MANAGER.updateCategory(id, name)) {

            System.out.println(
                    "Category updated successfully.");

        } else {

            System.out.println(
                    "Unable to update category.");
        }
    }

    // =========================================================
    // DELETE CATEGORY
    // =========================================================

    private static void deleteCategory() {

        System.out.println("\n----------- DELETE CATEGORY -----------");

        int id =
                readInt("Enter category ID: ");

        Category category =
                MANAGER.findCategoryById(id);

        if (category == null) {

            System.out.println(
                    "Category not found.");

            return;
        }

        System.out.println(category);

        String confirmation =
                readString(
                        "Are you sure? (yes/no): ");

        if (!confirmation.equalsIgnoreCase("yes")) {

            System.out.println(
                    "Delete operation cancelled.");

            return;
        }

        if (MANAGER.deleteCategory(id)) {

            System.out.println(
                    "Category deleted successfully.");

        } else {

            System.out.println(
                    "Cannot delete category.");

            System.out.println(
                    "It may be used by a product.");
        }
    }

    // =========================================================
    // STOCK MENU
    // =========================================================

    private static void stockMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n------------------------------------------");
            System.out.println("             STOCK MANAGEMENT");
            System.out.println("------------------------------------------");

            System.out.println("1. Add Stock");
            System.out.println("2. Remove Stock");
            System.out.println("3. View Products");
            System.out.println("4. Back");

            int choice =
                    readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStock();
                case 2 -> removeStock();
                case 3 -> MANAGER.displayProducts();
                case 4 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================================
    // ADD STOCK
    // =========================================================

    private static void addStock() {

        System.out.println("\n----------- ADD STOCK -----------");

        int id =
                readInt("Enter product ID: ");

        Product product =
                MANAGER.findProductById(id);

        if (product == null) {

            System.out.println(
                    "Product not found.");

            return;
        }

        int quantity =
                readInt("Enter quantity to add: ");

        if (MANAGER.addStock(id, quantity)) {

            System.out.println(
                    "Stock added successfully.");

            System.out.println(
                    "New stock: "
                            + product.getStock());

        } else {

            System.out.println(
                    "Invalid quantity.");
        }
    }

    // =========================================================
    // REMOVE STOCK
    // =========================================================

    private static void removeStock() {

        System.out.println("\n----------- REMOVE STOCK -----------");

        int id =
                readInt("Enter product ID: ");

        Product product =
                MANAGER.findProductById(id);

        if (product == null) {

            System.out.println(
                    "Product not found.");

            return;
        }

        System.out.println(
                "Current stock: "
                        + product.getStock());

        int quantity =
                readInt("Enter quantity to remove: ");

        if (MANAGER.removeStock(id, quantity)) {

            System.out.println(
                    "Stock removed successfully.");

            System.out.println(
                    "Remaining stock: "
                            + product.getStock());

        } else {

            System.out.println(
                    "Unable to remove stock.");

            System.out.println(
                    "Check the quantity and available stock.");
        }
    }

    // =========================================================
    // INPUT METHODS
    // =========================================================

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        SCANNER.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        SCANNER.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount.");
            }
        }
    }

    private static String readString(String message) {

        while (true) {

            System.out.print(message);

            String value =
                    SCANNER.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                    "Value cannot be empty.");
        }
    }
}