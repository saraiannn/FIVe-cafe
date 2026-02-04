package it.fiv.FIVecafe.control;

import it.fiv.FIVecafe.entity.BasicBeverage;
import it.fiv.FIVecafe.entity.Beverage;

public class BeverageFactory {

    public static Beverage createBeverage(int selection) {

        return switch (selection) {

            // basic coffee
            case 1  -> new BasicBeverage("Espresso", 1.20);
            case 2  -> new BasicBeverage("Americano", 1.40);
            case 3  -> new BasicBeverage("Cappuccino", 1.80);
            case 4  -> new BasicBeverage("Macchiato", 1.30);
            case 12 -> new BasicBeverage("Flat White", 1.90);
            case 13 -> new BasicBeverage("Latte", 1.90);
            case 14 -> new BasicBeverage("Double Espresso", 1.70);

            // cold coffee
            case 5  -> new BasicBeverage("Iced Latte", 2.00);
            case 6  -> new BasicBeverage("Cold Brew", 2.20);
            case 7  -> new BasicBeverage("Iced Americano", 2.30);
            case 8  -> new BasicBeverage("Iced Mocha", 2.70);
            case 15 -> new BasicBeverage("Iced Cappuccino", 2.40);
            case 16 -> new BasicBeverage("Espresso Tonic", 2.50);
            case 17 -> new BasicBeverage("Iced Chocolate Latte", 2.80);

            // tea & non coffee
            case 9  -> new BasicBeverage("Green Tea", 1.20);
            case 10 -> new BasicBeverage("Hot Chocolate", 2.50);
            case 11 -> new BasicBeverage("Black Tea", 1.20);
            case 18 -> new BasicBeverage("Chai Latte", 2.30);
            case 19 -> new BasicBeverage("Herbal Tea", 1.30);
            case 20 -> new BasicBeverage("Matcha Latte", 2.80);
            case 21 -> new BasicBeverage("Golden Milk", 2.60);

            // sweet drinks
            case 22 -> new BasicBeverage("Vanilla Latte", 2.40);
            case 23 -> new BasicBeverage("Caramel Latte", 2.50);
            case 24 -> new BasicBeverage("Hazelnut Latte", 2.50);
            case 25 -> new BasicBeverage("Mocha", 2.60);
            case 26 -> new BasicBeverage("White Mocha", 2.70);
            case 27 -> new BasicBeverage("Cinnamon Latte", 2.40);

            // refreshers
            case 28 -> new BasicBeverage("Lemonade", 2.00);
            case 29 -> new BasicBeverage("Sparkling Lemonade", 2.20);
            case 30 -> new BasicBeverage("Peach Iced Tea", 2.10);
            case 31 -> new BasicBeverage("Lemon Iced Tea", 2.10);
            case 32 -> new BasicBeverage("Orange Juice", 2.30);
            case 33 -> new BasicBeverage("Sparkling Water", 1.50);
            case 34 -> new BasicBeverage("Still Water", 1.20);

            // seasonal specials
            case 35 -> new BasicBeverage("Pumpkin Spice Latte", 2.90);
            case 36 -> new BasicBeverage("Gingerbread Latte", 2.90);
            case 37 -> new BasicBeverage("Peppermint Mocha", 3.00);
            case 38 -> new BasicBeverage("Salted Caramel Mocha", 3.00);
            case 39 -> new BasicBeverage("Affogato", 3.20);

            default -> throw new IllegalArgumentException("Invalid beverage selection: " + selection);
        };
    }
}

