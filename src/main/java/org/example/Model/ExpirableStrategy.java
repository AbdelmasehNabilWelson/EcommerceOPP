package org.example.Model;

import org.example.interfaces.ExpiryStrategy;

import java.time.LocalDateTime;

public class ExpirableStrategy implements ExpiryStrategy {
    private LocalDateTime expiryDate;

    public ExpirableStrategy(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "ExpirableStrategy { " +
                " expiryDate = " + expiryDate +
                " }";
    }
}
