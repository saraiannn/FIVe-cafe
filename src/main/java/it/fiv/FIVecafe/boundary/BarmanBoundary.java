package it.fiv.FIVecafe.boundary;

import it.fiv.FIVecafe.control.OrderController;
import it.fiv.FIVecafe.entity.Order;
import it.fiv.FIVecafe.entity.OrderStatus;
import it.fiv.FIVecafe.observer.OrderObserver;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class BarmanBoundary implements OrderObserver {

    private final OrderController orderController;

    private final ObservableList<Order> orderItems = FXCollections.observableArrayList();
    private ListView<Order> ordersView;

    public BarmanBoundary(OrderController orderController) {
        this.orderController = orderController;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Barman's Order Display");

        orderItems.setAll(orderController.getOrders());

        ordersView = new ListView<>(orderItems);
        ordersView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Order o, boolean empty) {
                super.updateItem(o, empty);
                if (empty || o == null) {
                    setText(null);
                } else {
                    setText("Order #" + o.getOrderNumber() + " | " + o.getStatus());
                }
            }
        });

        Button receivedBtn = new Button("RECEIVED");
        Button prepBtn = new Button("PREPARING");
        Button readyBtn = new Button("READY");

        receivedBtn.getStyleClass().add("secondary-btn");
        prepBtn.getStyleClass().add("primary-btn");
        readyBtn.getStyleClass().add("primary-btn");

        receivedBtn.setOnAction(e -> changeStatus(OrderStatus.RECEIVED));
        prepBtn.setOnAction(e -> changeStatus(OrderStatus.PREPARING));
        readyBtn.setOnAction(e -> changeStatus(OrderStatus.READY));

        HBox actions = new HBox(10, receivedBtn, prepBtn, readyBtn);
        actions.setPadding(new Insets(12));

        BorderPane root = new BorderPane();
        root.setCenter(ordersView);
        root.setBottom(actions);

        Scene scene = new Scene(root, 450, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();

        orderController.addObserver(this);
    }

    private void changeStatus(OrderStatus status) {
        Order selected = ordersView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        orderController.updateOrderStatus(selected, status);
    }


    @Override
    public void update(Order order) {
        Platform.runLater(() -> {
            if (!orderItems.contains(order)) {
                orderItems.add(order);
            }
            ordersView.refresh();
        });
    }
}


