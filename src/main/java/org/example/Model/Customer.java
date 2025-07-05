package org.example.Model;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deductBalance(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("wrong balance. Required: " + amount + ", Available: " + balance);
        }
        balance -= amount;
    }
}
