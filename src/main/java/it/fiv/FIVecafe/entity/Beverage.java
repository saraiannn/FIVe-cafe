package it.fiv.FIVecafe.entity;  //this class belongs to the Entity layer

public class Beverage {
    String beverageName;
    double beveragePrice;

    public Beverage(String beverageName, double beveragePrice) {
        this.beverageName = beverageName;
        this.beveragePrice = beveragePrice;
    }

    //both beverageName and beveragePrice are read-only by other layers
    public String getBeverageName() {
        return beverageName;
    }
    public double getBeveragePrice() {
        return beveragePrice;
    }
}

