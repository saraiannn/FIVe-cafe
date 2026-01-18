package it.fiv.FIVecafe.entity;

public class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getBeverageName() {
        return beverage.getBeverageName() + "+ decorator";
    }

    @Override
    public double getBeveragePrice() {
        return beverage.getBeveragePrice() + 0.10;
    }
}
