package org.example.Model;

import org.example.interfaces.ShippingStrategy;

public class ShippableStrategy implements ShippingStrategy {
    private double weight;
    private double shippingCost;

    public ShippableStrategy(double weight, double shippingCost) {
        this.weight = weight;
        this.shippingCost = shippingCost;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Override
    public String toString() {
        return "ShippableStrategy{" +
                "weight=" + weight +
                ", shippingCost=" + shippingCost +
                '}';
    }
}
