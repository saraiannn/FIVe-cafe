package it.fiv.FIVecafe.boundary; //this class belongs to the Boundary layer

import it.fiv.FIVecafe.control.OrderManager;
import it.fiv.FIVecafe.entity.BasicBeverage;
import it.fiv.FIVecafe.entity.Beverage;
import it.fiv.FIVecafe.entity.MilkDecorator;

import java.util.Scanner;

public class TotemInterface {

    private OrderManager orderManager = new OrderManager();

    public void start(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to FIVe Cafè");
        System.out.println("=== MENU ===");
        System.out.println("1. Espresso   1.00");
        System.out.println("2. Macchiato  1.10");;

        int selection = scanner.nextInt();
        Beverage beverage; //user choice passed from boundary to control

        if (selection == 1) {
            beverage = new BasicBeverage("Espresso", 1.00);
            beverage = new MilkDecorator(beverage);
        } else {
            beverage = new BasicBeverage("Macchiato", 1.10);
        }

        orderManager.placeOrder(beverage);
    }
}