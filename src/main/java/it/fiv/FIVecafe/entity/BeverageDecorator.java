package it.fiv.FIVecafe.entity;

public abstract class BeverageDecorator implements Beverage { //wraps Beverage
    protected Beverage beverage;

    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }


}
