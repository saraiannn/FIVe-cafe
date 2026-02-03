package it.fiv.FIVecafe.control;

import it.fiv.FIVecafe.entity.Order;
import it.fiv.FIVecafe.entity.OrderStatus;
import it.fiv.FIVecafe.observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {  //subject

    private List<OrderObserver> observers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private int nextOrderNumber = 1;

    public void addObserver(OrderObserver observer){
        observers.add(observer);
    }


    public void notifyObservers(Order order){
        for(OrderObserver observer : observers){
            observer.update(order);
        }
    }

    public Order startNewOrder(){
        Order order = new Order(nextOrderNumber++);
        orders.add(order);
        notifyObservers(order);
        return order;
    }


    public void updateOrderStatus(Order order, OrderStatus next) {
        if (order == null || next == null) return;

        OrderStatus current = order.getStatus();

        if (current == next) return;

        boolean allowed =
                (current == OrderStatus.CREATED   && next == OrderStatus.RECEIVED) ||
                        (current == OrderStatus.RECEIVED  && next == OrderStatus.PREPARING) ||
                        (current == OrderStatus.PREPARING && next == OrderStatus.READY);


        if (!allowed) {
            return;
        }

        order.setStatus(next);
        notifyObservers(order);
    }


    public List<Order> getOrders() {
        return orders;
    }

    public void submitOrder(Order order) {
        if (order == null) return;
        order.setStatus(OrderStatus.RECEIVED);
        notifyObservers(order);
    }
}
