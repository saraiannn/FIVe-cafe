package it.fiv.FIVecafe.control;

import it.fiv.FIVecafe.entity.Beverage;

public class OrderManager {
    public void createOrder(Beverage beverage){
        System.out.println("Order placed!");
        System.out.println("Beverage: " + beverage.getBeverageName());
        System.out.println("Price: " + beverage.getBeveragePrice() + " €");
    }
}
