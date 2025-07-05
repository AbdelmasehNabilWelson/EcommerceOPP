package org.example.Service;

import org.example.interfaces.ShippingItem;
import java.util.List;

public class ShippmentService {
    public static void processShipment(List<ShippingItem> items) {
        if (items.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (ShippingItem item : items) {
            System.out.println(item.getName() + " " + (item.getWeight() / 1000) + "kg");
            totalWeight += item.getWeight();
        }

        System.out.println("Total package weight " + (totalWeight / 1000) + "kg");
    }
}
