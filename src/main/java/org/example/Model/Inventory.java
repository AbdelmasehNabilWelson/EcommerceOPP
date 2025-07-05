package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayProducts() {
        System.out.println("========================================================");
        System.out.println("Existing Inventory Items: ");
        for (int i = 0; i < products.size(); i++) {
            System.out.print("Item " + i + ": ");
            System.out.println(products.get(i));
        }
        System.out.println("End of Inventory items");
        System.out.println("========================================================");
    }

    public void requireAmountOfProduct(int productID, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount Must be greater than zero");
        }

        if (productID < 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }

        Product product = products.get(productID);

        if (product.getQuantity() < amount) {
            throw new IllegalArgumentException("Not enough products existing");
        }

        product.setQuantity(product.getQuantity() - amount);
    }
}
