package it.fiv.FIVecafe.entity;

public abstract class BeverageDecorator implements Beverage {  //(abstract decorator) wraps all beverages
    protected Beverage beverage;

    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

}
