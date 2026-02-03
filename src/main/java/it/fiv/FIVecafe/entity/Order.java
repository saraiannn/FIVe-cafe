package it.fiv.FIVecafe.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int orderNumber;
    private final List<Beverage> beverages;
    private OrderStatus status = OrderStatus.CREATED;


    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
        this.beverages = new ArrayList<>();
        this.status = OrderStatus.CREATED;

    }

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(Beverage beverage : beverages) {
            totalPrice += beverage.getBeveragePrice();
        }
        return totalPrice;
    }

    public String getSummary() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Order #").append(orderNumber).append("\n");
        for(Beverage beverage : beverages) {
            stringBuilder.append(beverage.getBeverageName()).append(" ").append(String.format("%.2f €", beverage.getBeveragePrice())).append("\n");
        }

        stringBuilder.append("Total: ").append(String.format("%.2f €", getTotalPrice())).append("\n");
        return stringBuilder.toString();
    }
}

