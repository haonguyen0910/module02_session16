package quanlysanpham_baikha;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductManager {
    private HashMap<Integer, Product> products = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (products.containsKey(id)) {
            System.out.println("Product ID already exists!");
            return;
        }

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Product product = new Product(id, name, price);
        products.put(id, product);
        System.out.println("Product added successfully.");
    }

    public void editProduct() {
        System.out.print("Enter Product ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (!products.containsKey(id)) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new Product Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Product product = new Product(id, name, price);
        products.put(id, product);
        System.out.println("Product updated successfully.");
    }

    public void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (products.remove(id) != null) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    public void filterProductsByPrice() {
        System.out.println("Products with price greater than 100:");

        products.values().stream()
                .filter(product -> product.getPrice() > 100)
                .forEach(System.out::println);
    }

    public void totalValue() {
        double total = products.values().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Total value of all products: " + total);
    }
}
