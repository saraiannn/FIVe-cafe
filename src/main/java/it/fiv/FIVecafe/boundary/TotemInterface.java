package it.fiv.FIVecafe.boundary;

import it.fiv.FIVecafe.control.Barman;
import it.fiv.FIVecafe.control.OrderManager;
import it.fiv.FIVecafe.entity.*;
import it.fiv.FIVecafe.control.BeverageFactory;
import it.fiv.FIVecafe.entity.Order;

import java.util.Scanner;

public class TotemInterface {
    private final OrderManager orderManager = new OrderManager();
    private final OrderDisplay display = new OrderDisplay();
    private final Barman barman = new Barman(orderManager);


    public void start() {

        orderManager.addObserver(display);
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("Welcome to FIVe Cafè!");
        System.out.println("*** MENU ***");
        System.out.println("*** BASIC COFFEE ***");
        System.out.println("1.  Espresso          1.20 €");
        System.out.println("2.  Americano         1.40 €");
        System.out.println("3.  Cappuccino        1.80 €");
        System.out.println("4.  Macchiato         1.30 €");

        System.out.println("*** COLD COFFEE ***");
        System.out.println("5.  Iced Latte        2.00 €");
        System.out.println("6.  Cold Brew         2.20 €");
        System.out.println("7.  Iced Americano    2.30 €");
        System.out.println("8.  Iced Mocha        2.70 €");

        System.out.println("*** TEA & NON COFFEE ***");
        System.out.println("9.  Green Tea         1.20 €");
        System.out.println("10. Hot Chocolate     2.50 €");
        System.out.println("11. Black Tea         1.20 €");

        System.out.println("*** EXTRAS ***");
        System.out.println("a. Milk               0.30 €");
        System.out.println("b. Sugar              0.10 €");
        System.out.println("c. Cocoa              0.40 €");
        System.out.println("d. Caramel            0.60 €");

        System.out.println("0.  Finish order/go to checkout");

        boolean running = true;

        Order order = orderManager.startNewOrder();

        while(running) {

            System.out.println("Select your beverage:");

            Beverage beverage = BeverageFactory.createBeverage(choice);
            choice = scanner.nextInt();
            scanner.nextLine();  //cleans buffer's scanner
            switch (choice) {
                case 1:
                    beverage = BeverageFactory.createBeverage(1);
                    break;
                case 2:
                    beverage = BeverageFactory.createBeverage(2);
                    break;
                case 3:
                    beverage = BeverageFactory.createBeverage(3);
                    break;
                case 4:
                    beverage = BeverageFactory.createBeverage(4);
                    break;
                case 5:
                    beverage = BeverageFactory.createBeverage(5);
                    break;
                case 6:
                    beverage = BeverageFactory.createBeverage(6);
                    break;
                case 7:
                    beverage = BeverageFactory.createBeverage(7);
                    break;
                case 8:
                    beverage = BeverageFactory.createBeverage(8);
                    break;
                case 9:
                    beverage = BeverageFactory.createBeverage(9);
                    break;
                case 10:
                    beverage = BeverageFactory.createBeverage(10);
                    break;
                case 11:
                    beverage = BeverageFactory.createBeverage(11);
                    break;
                case 0:
                    running = false;
                    continue;
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }

            System.out.println("Add milk? (y/n)");
            String input = scanner.nextLine();
            boolean addMilk = input.equalsIgnoreCase("y");
            if (addMilk) {
                beverage = new MilkDecorator(beverage);
            }

            System.out.println("Add sugar? (y/n)");
            input = scanner.nextLine();
            boolean addSugar = input.equalsIgnoreCase("y");
            if (addSugar) {
                beverage = new SugarDecorator(beverage);
            }

            System.out.println("Add cocoa? (y/n)");
            input = scanner.nextLine();
            boolean addCocoa = input.equalsIgnoreCase("y");
            if (addCocoa) {
                beverage = new CocoaDecorator(beverage);
            }

            System.out.println("Add caramel? (y/n)");
            input = scanner.nextLine();
            boolean addCaramel = input.equalsIgnoreCase("y");
            if (addCaramel) {
                beverage = new CaramelDecorator(beverage);
            }


            order.addBeverage(beverage);

            System.out.println("Wanna add something else?");

            String addBeverage = scanner.nextLine();
            if (addBeverage.equals("no")) {
                break;
            }

        }
        System.out.println("*** ORDER SUMMARY ***");
        System.out.println(order.getSummary());

        barman.prepareOrder(order);
        System.out.println("Thank you for choosing FIVe Cafè!");
    }

}

