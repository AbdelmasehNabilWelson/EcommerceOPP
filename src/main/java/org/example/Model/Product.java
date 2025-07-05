package org.example.Model;

import org.example.interfaces.ExpiryStrategy;
import org.example.interfaces.ShippingStrategy;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private ShippingStrategy shippingStrategy;
    private ExpiryStrategy expiryStrategy;

    public Product(String name, double price, int quantity, ShippingStrategy shippingStrategy, ExpiryStrategy expiryStrategy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shippingStrategy = shippingStrategy;
        this.expiryStrategy = expiryStrategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShippingStrategy getShippingStrategy() {
        return shippingStrategy;
    }

    public void setShippingStrategy(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public ExpiryStrategy getExpiryStrategy() {
        return expiryStrategy;
    }

    public void setExpiryStrategy(ExpiryStrategy expiryStrategy) {
        this.expiryStrategy = expiryStrategy;
    }

    public boolean isExpired() {
        return expiryStrategy.isExpired();
    }

    public boolean isShippable() {
        return shippingStrategy.isShippable();
    }

    public double getWeight() {
        return shippingStrategy.getWeight();
    }

    public double getShippingCost() {
        if (shippingStrategy instanceof ShippableStrategy) {
            return ((ShippableStrategy) shippingStrategy).getShippingCost();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Product{ " +
                "name = '" + name + '\'' +
                ", price = " + price +
                ", quantity = " + quantity +
                ", shippingStrategy = " + shippingStrategy +
                ", expiryStrategy = " + expiryStrategy +
                " }";
    }
}
