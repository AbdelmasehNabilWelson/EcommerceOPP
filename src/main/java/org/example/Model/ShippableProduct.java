package org.example.Model;

import org.example.interfaces.ShippingItem;

public class ShippableProduct implements ShippingItem {
    private String name;
    private double weight;
    private int quantity;
    
    public ShippableProduct(String name, double weight, int quantity) {
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
    }
    
    @Override
    public String getName() {
        return quantity + "x " + name;
    }
    
    @Override
    public double getWeight() {
        return weight * quantity;
    }
}
