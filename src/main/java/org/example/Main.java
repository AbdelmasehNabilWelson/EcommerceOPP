package org.example;

import org.example.Model.Cart;
import org.example.Model.Customer;
import org.example.Model.ExpirableStrategy;
import org.example.Model.NonExpirableStrategy;
import org.example.Model.NonShippableStrategy;
import org.example.Model.Product;
import org.example.Model.ShippableStrategy;
import org.example.Service.CheckoutService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Product cheese = new Product(
                "Cheese", 100,
                500,
                new ShippableStrategy(400, 15),
                new ExpirableStrategy(LocalDateTime.now().plusDays(7))
        );

        Product biscuits = new Product(
                "Biscuits", 150,
                300,
                new ShippableStrategy(700, 15),
                new ExpirableStrategy(LocalDateTime.now().plusDays(10))
        );

        Product tv = new Product(
                "TV",
                2000,
                50,
                new ShippableStrategy(5000, 100),
                new NonExpirableStrategy()
        );

        Product scratchCard = new Product(
                "Mobile Scratch Card",
                50,
                1000,
                new NonShippableStrategy(),
                new ExpirableStrategy(LocalDateTime.now().plusDays(365))
        );


        System.out.println("=== Test Case 1: Successful Checkout ===");
        testSuccessfulCheckout(cheese, biscuits, tv, scratchCard);


        System.out.println("\n=== Test Case 2: Empty Cart ===");
        testEmptyCart();


        System.out.println("\n=== Test Case 3: Not Enough Balance ===");
        testInsufficientBalance(cheese, tv);


        System.out.println("\n=== Test Case 4: Expired Product ===");
        testExpiredProduct();


        System.out.println("\n=== Test Case 5: Out of Stock ===");
        testOutOfStock(cheese);
    }

    private static void testSuccessfulCheckout(Product cheese, Product biscuits, Product tv, Product scratchCard) {
        try {
            Cart cart = new Cart();
            Customer customer = new Customer("Ali hassan", 5000);

            cart.addProduct(cheese, 2);
            cart.addProduct(biscuits, 1);
            cart.addProduct(tv, 1);
            cart.addProduct(scratchCard, 1);

            CheckoutService.checkout(customer, cart);
            System.out.println("✓ Checkout successful");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private static void testEmptyCart() {
        try {
            Cart cart = new Cart();
            Customer customer = new Customer("Ali hassan", 1000);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("✓ Expected error caught: " + e.getMessage());
        }
    }

    private static void testInsufficientBalance(Product cheese, Product tv) {
        try {
            Cart cart = new Cart();
            Customer customer = new Customer("Ali hassan", 100);

            cart.addProduct(cheese, 1);
            cart.addProduct(tv, 1);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("✓ Expected error caught: " + e.getMessage());
        }
    }

    private static void testExpiredProduct() {
        try {
            Product expiredProduct = new Product(
                    "Expired Milk", 50, 10,
                    new ShippableStrategy(500, 10),
                    new ExpirableStrategy(LocalDateTime.now().minusDays(1))
            );

            Cart cart = new Cart();
            Customer customer = new Customer("Mickel Shenouda", 1000);

            cart.addProduct(expiredProduct, 1);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("✓ Expected error caught: " + e.getMessage());
        }
    }

    private static void testOutOfStock(Product cheese) {
        try {
            Cart cart = new Cart();
            Customer customer = new Customer("Seha Nabil", 10000);

            cart.addProduct(cheese, 1000);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("✓ Expected error caught: " + e.getMessage());
        }
    }
}