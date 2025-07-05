package org.example.Model;

import org.example.interfaces.ExpiryStrategy;

import java.time.LocalDateTime;

public class NonExpirableStrategy implements ExpiryStrategy {

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public String toString() {
        return "Non-expirable";
    }
}
