package org.example.Model;

import org.example.interfaces.ShippingStrategy;

public class NonShippableStrategy implements ShippingStrategy {
    @Override
    public boolean isShippable() {
        return false;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public String toString() {
        return "Non-shippable";
    }
}
