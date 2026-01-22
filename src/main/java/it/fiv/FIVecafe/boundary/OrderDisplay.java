package it.fiv.FIVecafe.boundary;

import it.fiv.FIVecafe.entity.Order;
import it.fiv.FIVecafe.entity.OrderStatus;
import it.fiv.FIVecafe.observer.OrderObserver;

public class OrderDisplay implements OrderObserver {

    @Override
    public void update(Order order) {
        if(order.getStatus() == OrderStatus.READY) {
            System.out.println("Order #" + order.getOrderNumber() + " is ready");
        }
    }
}
