package it.fiv.FIVecafe.boundary;

import it.fiv.FIVecafe.control.OrderManager;
import it.fiv.FIVecafe.entity.BasicBeverage;
import it.fiv.FIVecafe.entity.Beverage;
import it.fiv.FIVecafe.entity.MilkDecorator;

import java.util.Scanner;

public class TotemInterface {
    private final OrderManager orderManager = new OrderManager();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*** MENU CAFE ***");
        System.out.println("1. Coffee   1.20 €");
        System.out.println("2. Tea      1.50 €");
        System.out.println("Select your beverage:");
        Beverage beverage = null;

        int choice = scanner.nextInt();
        scanner.nextLine();  //cleans buffer's scanner
        switch (choice) {
            case 1:
                beverage = new BasicBeverage("Espresso", 1.20);  //user choice passed from boundary to control
                break;
            case 2:
                beverage = new BasicBeverage("Tea", 1.50);  //user choice passed from boundary to control
                break;
            default:
                System.out.println("Wrong choice");
                return;
        }

        System.out.println("Wanna add a decorator?");
        String selection = scanner.nextLine();

        if  (selection.equals("yes")) {
            beverage = new MilkDecorator(beverage);
        }
        System.out.printf("%s - %.2f €%n", beverage.getBeverageName(), beverage.getBeveragePrice());
        orderManager.createOrder(beverage);
    }

}
