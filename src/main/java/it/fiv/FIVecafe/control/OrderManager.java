package it.fiv.FIVecafe.control;

import it.fiv.FIVecafe.entity.Beverage;
import it.fiv.FIVecafe.entity.Order;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class OrderManager {
    private final List<Order> orders = new ArrayList<>();

    public void createOrder(Order order) {
        orders.add(order);

        System.out.println("*** ORDER SUMMARY ***");
        for(Beverage b: order.getBeverages()) {
        System.out.printf("%s : %.2f €%n", b.getBeverageName(), b.getBeveragePrice());
        }
        System.out.printf("TOTAL: %.2f €%n", order.getTotalPrice());
    }

    public List<Order> getOrders() {
        return orders;
    }


}
