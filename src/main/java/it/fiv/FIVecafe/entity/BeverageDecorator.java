package it.fiv.FIVecafe.entity;

public abstract class BeverageDecorator implements Beverage {  //wraps Beverage
    protected Beverage beverage;

    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getBeverageName() {
        return beverage.getBeverageName();
    }

    @Override
    public double getBeveragePrice() {
        return beverage.getBeveragePrice();
    }

}
