package it.fiv.FIVecafe.boundary; //this class belongs to the Boundary layer

import it.fiv.FIVecafe.control.OrderManager;
import it.fiv.FIVecafe.entity.Beverage;

import java.util.Scanner;

public class TotemInterface {
    
    private OrderManager orderManager = new OrderManager();

    public void start() {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to FIVe Cafè");
        System.out.println("=== MENU ===");
        System.out.println("1. Espresso   1.00");
        System.out.println("2. Macchiato  1.10");

        int selection = scanner.nextInt();
        Beverage beverage;  //user choice passed from boundary to control

        if (selection == 1) {
            beverage = new Beverage("Espresso", 1.00);
        } else {
            beverage = new Beverage("Macchiato", 1.10);
        }
        
        orderManager.createOrder(beverage);
    }
}
