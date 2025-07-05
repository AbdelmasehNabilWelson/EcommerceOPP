package org.example.Service;

import org.example.Model.Cart;
import org.example.Model.*;
import org.example.Model.Customer;
import org.example.Model.Product;
import org.example.Model.ShippableProduct;
import org.example.interfaces.ShippingItem;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        
        validateCartProducts(cart);
        
        double subtotal = cart.getSubtotal();
        double shippingFees = cart.getShippingFees();
        double totalAmount = cart.getTotalAmount();
        

        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Customer's balance is insufficient. Required: " + 
                totalAmount + ", Available: " + customer.getBalance());
        }
//
        List<ShippingItem> shippableItems = collectShippableItems(cart);
        if (!shippableItems.isEmpty()) {
            ShippmentService.processShipment(shippableItems);
        }
        
        customer.deductBalance(totalAmount);
        
        updateInventory(cart);
        
        printCheckoutReceipt(cart, subtotal, shippingFees, totalAmount, customer.getBalance());
    }
    
    private static void validateCartProducts(Cart cart) {
        for (CartProduct cartProduct : cart.getProducts()) {
            Product product = cartProduct.getProduct();
            
            if (product.isExpired()) {
                throw new IllegalStateException("Product " + product.getName() + " is expired");
            }
            
            if (cartProduct.getQuantity() > product.getQuantity()) {
                throw new IllegalStateException("Product " + product.getName() + " is out of stock. " +
                    "Required: " + cartProduct.getQuantity() + ", Available: " + product.getQuantity());
            }
        }
    }
    
    private static List<ShippingItem> collectShippableItems(Cart cart) {
        List<ShippingItem> shippableItems = new ArrayList<>();
        
        for (CartProduct cartProduct : cart.getProducts()) {
            Product product = cartProduct.getProduct();
            if (product.isShippable()) {
                shippableItems.add(new ShippableProduct(
                    product.getName(),
                    product.getWeight(),
                    cartProduct.getQuantity()
                ));
            }
        }
        
        return shippableItems;
    }
    
    private static void updateInventory(Cart cart) {
        for (CartProduct cartProduct : cart.getProducts()) {
            Product product = cartProduct.getProduct();
            product.setQuantity(product.getQuantity() - cartProduct.getQuantity());
        }
    }
    
    private static void printCheckoutReceipt(Cart cart, double subtotal, double shippingFees, 
                                           double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");
        
        for (CartProduct cartProduct : cart.getProducts()) {
            Product product = cartProduct.getProduct();
            System.out.println(cartProduct.getQuantity() + "x " + product.getName() + " " + 
                (product.getPrice() * cartProduct.getQuantity()));
        }
        
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFees);
        System.out.println("Amount " + totalAmount);
        System.out.println("Customer balance after payment: " + remainingBalance);
        System.out.println("END.");
    }
}
