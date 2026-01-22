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


    public void updateOrderStatus(Order order, OrderStatus status) {
        order.setStatus(status);
        notifyObservers(order);
    }

}
