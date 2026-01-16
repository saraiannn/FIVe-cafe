package it.fiv.FIVecafe.control; //this class belongs to the Control layer

import it.fiv.FIVecafe.entity.Beverage;

public class OrderManager {
    public void placeOrder(Beverage beverage){
        System.out.println("Order placed!");
        System.out.println("Beverage: " + beverage.getName());
        System.out.printf("Price: %.2f €%n", beverage.getBeveragePrice());
    }
}
