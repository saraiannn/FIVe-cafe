package it.fiv.FIVecafe.control;

import it.fiv.FIVecafe.entity.BasicBeverage;
import it.fiv.FIVecafe.entity.Beverage;

public class BeverageFactory {

    public static Beverage createBeverage(int selection){
        return switch (selection) {  //using enhanced switch statement
            case 1 -> new BasicBeverage("Espresso", 1.20);
            case 2 -> new BasicBeverage("Americano", 1.40);
            case 3 -> new BasicBeverage("Cappuccino", 1.80);
            case 4 -> new BasicBeverage("Macchiato", 1.30);
            case 5 -> new BasicBeverage("Iced Latte", 2.00);
            case 6 -> new BasicBeverage("Cold Brew", 2.20);
            case 7 -> new BasicBeverage("Iced Americano", 2.30);
            case 8 -> new BasicBeverage("Iced Mocha", 2.70);
            case 9 -> new BasicBeverage("Green Tea", 1.20);
            case 10 -> new BasicBeverage("Hot Chocolate", 2.50);
            case 11 -> new BasicBeverage("Black Tea", 1.20);
            default -> null;

        };
    }
}
