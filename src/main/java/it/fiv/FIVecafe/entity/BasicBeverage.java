package it.fiv.FIVecafe.entity;

public class BasicBeverage implements Beverage {//basic beverage without extras that implements the Component
    private final String beverageName;
    private final double beveragePrice;

    public BasicBeverage(String beverageName, double beveragePrice) {
        this.beverageName = beverageName;
        this.beveragePrice = beveragePrice;
    }

    @Override
    public String getBeverageName() {
        return beverageName;
    }

    @Override
    public double getBeveragePrice() {
        return beveragePrice;
    }

}
