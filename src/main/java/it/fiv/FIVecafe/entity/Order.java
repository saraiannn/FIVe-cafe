package it.fiv.FIVecafe.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Beverage> beverages = new ArrayList<>();

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(Beverage beverage : beverages) {
            totalPrice += beverage.getBeveragePrice();
        }
        return totalPrice;
    }
}
