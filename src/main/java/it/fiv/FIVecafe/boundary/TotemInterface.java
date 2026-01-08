package it.fiv.FIVecafe.boundary;

import it.fiv.FIVecafe.control.OrderManager;
import it.fiv.FIVecafe.entity.Beverage;

import java.util.Scanner;

public class TotemInterface {
    private OrderManager orderManager = new OrderManager();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*** MENU CAFE ***");
        System.out.println("1. Coffee   1.20 €");
        System.out.println("2. Tea      1.00 €");

        int choice = scanner.nextInt();
        Beverage beverage;  //user choice passed from boundary to control

        if (choice == 1) {
            beverage = new Beverage("Coffee", 1.20);
        } else {
            beverage = new Beverage("Tea", 1.00);
        }
        orderManager.createOrder(beverage);
    }
}
