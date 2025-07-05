package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartProduct> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Cannot add " + quantity + " items. Only " + 
                product.getQuantity() + " " + product.getName() + " available in stock");
        }
        
        // Check if product already exists in cart
        for (CartProduct cartProduct : products) {
            if (cartProduct.getProduct().getName().equals(product.getName())) {
                int newQuantity = cartProduct.getQuantity() + quantity;
                if (newQuantity > product.getQuantity()) {
                    throw new IllegalArgumentException("Cannot add " + quantity + " more items. Only " + 
                        (product.getQuantity() - cartProduct.getQuantity()) + " " + product.getName() + " available");
                }
                cartProduct.setQuantity(newQuantity);
                return;
            }
        }
        
        products.add(new CartProduct(product, quantity));
    }

    public List<CartProduct> getProducts() {
        return products;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public double getSubtotal() {
        return products.stream()
                .mapToDouble(cp -> cp.getProduct().getPrice() * cp.getQuantity())
                .sum();
    }

    public double getShippingFees() {
        return products.stream()
                .filter(cp -> cp.getProduct().isShippable())
                .mapToDouble(cp -> cp.getProduct().getShippingCost())
                .sum();
    }

    public double getTotalAmount() {
        return getSubtotal() + getShippingFees();
    }
}
