package it.fiv.FIVecafe.control;

import it.fiv.FIVecafe.entity.Order;
import it.fiv.FIVecafe.entity.OrderStatus;

public class Kitchen {

    private final OrderManager orderManager;

    public Kitchen(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public void prepareOrder(Order order) {
        orderManager.updateOrderStatus(order, OrderStatus.PREPARING);

        System.out.println("Preparing order #" + order.getOrderNumber());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        order.setStatus(OrderStatus.READY);

        orderManager.updateOrderStatus(order, OrderStatus.READY);
    }

}
