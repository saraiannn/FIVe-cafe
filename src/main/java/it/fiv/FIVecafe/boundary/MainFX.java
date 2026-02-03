package it.fiv.FIVecafe.boundary;

import it.fiv.FIVecafe.control.OrderManager;
import it.fiv.FIVecafe.control.BeverageFactory;

import it.fiv.FIVecafe.entity.Order;
import it.fiv.FIVecafe.entity.Beverage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import it.fiv.FIVecafe.entity.MilkDecorator;
import it.fiv.FIVecafe.entity.SugarDecorator;
import it.fiv.FIVecafe.entity.CaramelDecorator;
import it.fiv.FIVecafe.entity.CocoaDecorator;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainFX extends Application {  //GUI entry point

    private OrderManager orderManager = new OrderManager();
    private Order currentOrder;

    private Button sendToKitchenBtn;

    @Override
    public void start(Stage stage) {

        Label title = new Label("FIVe Cafè's totem");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Button startOrderBtn = new Button("Start Order");

        VBox homeLayout = new VBox(20, title, startOrderBtn);
        homeLayout.setStyle("-fx-alignment: center;");

        Scene homeScene = new Scene(homeLayout, 600, 400);

        // opens KitchenDisplay window when the program starts
        new BarmanDisplayFX(orderManager).show();

        startOrderBtn.setOnAction(e -> {
            currentOrder = orderManager.startNewOrder();
            Scene orderScene = createOrderScene(stage);
            stage.setScene(orderScene);
        });

        stage.setScene(homeScene);
        stage.setTitle("FIVe Cafè");
        stage.show();
    }

    private Scene createOrderScene(Stage stage) {

        BorderPane root = new BorderPane();

        // categories (left side)
        VBox categoryBox = new VBox(10);
        categoryBox.setStyle("-fx-padding: 15; -fx-background-color: #eeeeee;");
        categoryBox.setPrefWidth(220);

        // beverages (right side)
        ListView<String> beverageList = new ListView<>();
        beverageList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // beverage details
        Label bevName = new Label("");
        bevName.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Label bevDesc = new Label("");
        bevDesc.setWrapText(true);

        CheckBox milkCb = new CheckBox("Milk (+0.30€)");
        CheckBox sugarCb = new CheckBox("Sugar (+0.10€)");
        CheckBox caramelCb = new CheckBox("Caramel (+0.60€)");
        CheckBox cocoaCb = new CheckBox("Cocoa (+0.40€)");

        VBox extrasBox = new VBox(6, new Label("Extras"), milkCb, sugarCb, caramelCb, cocoaCb);

        extrasBox.setDisable(true);
        milkCb.setSelected(false);
        sugarCb.setSelected(false);
        caramelCb.setSelected(false);
        cocoaCb.setSelected(false);

        sendToKitchenBtn = new Button("Send to Kitchen");
        sendToKitchenBtn.setDisable(true);
        sendToKitchenBtn.getStyleClass().add("primary-btn"); // css

        sendToKitchenBtn.setOnAction(e -> {
            if (currentOrder == null) return;

            orderManager.submitOrder(currentOrder);

            Alert ok = new Alert(Alert.AlertType.INFORMATION);
            ok.setHeaderText(null);
            ok.setContentText("Order sent to kitchen! Status: " + currentOrder.getStatus());
            ok.showAndWait();
        });

        Button addToCartBtn = new Button("Add to cart");
        Button cancelBtn = new Button("Cancel");

        addToCartBtn.setDisable(true);
        cancelBtn.setDisable(true);

        HBox actions = new HBox(10, addToCartBtn, cancelBtn, sendToKitchenBtn);

        VBox detailsBox = new VBox(10, bevName, bevDesc, extrasBox, actions);
        detailsBox.setPadding(new Insets(15));
        detailsBox.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1;");


        detailsBox.setVisible(false);
        detailsBox.setManaged(false);


        VBox rightBox = new VBox(10, beverageList, detailsBox);
        rightBox.setPadding(new Insets(15));
        root.setCenter(rightBox);

        Map<String, String[]> categoryToBeverages = new LinkedHashMap<>();
        categoryToBeverages.put("Basic Coffee", new String[]{"Espresso", "Americano", "Cappuccino", "Macchiato"});
        categoryToBeverages.put("Cold Coffee", new String[]{"Iced Latte", "Cold Brew", "Iced Americano", "Iced Mocha"});
        categoryToBeverages.put("Tea & Non Coffee", new String[]{"Green Tea", "Hot Chocolate", "Black Tea"});

        // category buttons
        for (String categoryName : categoryToBeverages.keySet()) {
            Button catBtn = new Button(categoryName);
            catBtn.setMaxWidth(Double.MAX_VALUE);

            catBtn.setOnAction(e -> {
                ObservableList<String> items = FXCollections.observableArrayList(categoryToBeverages.get(categoryName));
                beverageList.setItems(items);

                detailsBox.setVisible(true);
                detailsBox.setManaged(true);

                beverageList.getSelectionModel().clearSelection();
                bevName.setText("Select a beverage");
                bevDesc.setText("");
                addToCartBtn.setDisable(true);
                cancelBtn.setDisable(true);


                sendToKitchenBtn.setDisable(currentOrder == null || currentOrder.getTotalPrice() <= 0);
            });

            categoryBox.getChildren().add(catBtn);
        }

        root.setLeft(categoryBox);

        beverageList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
            if (selected == null) {
                bevName.setText("Select a beverage");
                bevDesc.setText("");
                addToCartBtn.setDisable(true);
                cancelBtn.setDisable(true);

                extrasBox.setDisable(true);
                milkCb.setSelected(false);
                sugarCb.setSelected(false);
                caramelCb.setSelected(false);
                cocoaCb.setSelected(false);

                return;
            }

            bevName.setText(selected);
            bevDesc.setText(getDescription(selected));
            addToCartBtn.setDisable(false);
            cancelBtn.setDisable(false);

            extrasBox.setDisable(false);
            milkCb.setSelected(false);
            sugarCb.setSelected(false);
            caramelCb.setSelected(false);
            cocoaCb.setSelected(false);
        });

        addToCartBtn.setOnAction(e -> {
            String selected = beverageList.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            int choice = mapBeverageToSelection(selected);

            Beverage beverage = BeverageFactory.createBeverage(choice);

            // decorators
            beverage = applyExtras(beverage,
                    milkCb.isSelected(),
                    sugarCb.isSelected(),
                    caramelCb.isSelected(),
                    cocoaCb.isSelected()
            );

            currentOrder.addBeverage(beverage);

            // enables "Send to kitchen" if order is not null
            sendToKitchenBtn.setDisable(currentOrder.getTotalPrice() <= 0);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(
                    beverage.getBeverageName() +
                            "\nItem: " + String.format("%.2f €", beverage.getBeveragePrice()) +
                            "\nOrder total: " + String.format("%.2f €", currentOrder.getTotalPrice())
            );
            alert.showAndWait();
        });

        cancelBtn.setOnAction(e -> {
            beverageList.getSelectionModel().clearSelection();
            beverageList.setItems(FXCollections.observableArrayList());

            detailsBox.setVisible(false);
            detailsBox.setManaged(false);

            bevName.setText("");
            bevDesc.setText("");
            addToCartBtn.setDisable(true);
            cancelBtn.setDisable(true);
        });

        return new Scene(root, 900, 550);
    }

    private Beverage applyExtras(Beverage base, boolean milk, boolean sugar, boolean caramel, boolean cocoa) {
        Beverage result = base;

        if (milk) result = new MilkDecorator(result);
        if (sugar) result = new SugarDecorator(result);
        if (caramel) result = new CaramelDecorator(result);
        if (cocoa) result = new CocoaDecorator(result);

        return result;
    }


    private void showBeverages(VBox beverageBox, String[] beverages) {

        beverageBox.getChildren().clear();

        for (String bev : beverages) {
            Button btn = new Button(bev);
            btn.setPrefWidth(200);

            btn.setOnAction(e -> {
                int selection = mapBeverageToSelection(bev);
                Beverage beverage = BeverageFactory.createBeverage(selection);
                currentOrder.addBeverage(beverage);

                sendToKitchenBtn.setDisable(currentOrder.getTotalPrice() <= 0);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText(
                        bev + " added!\nTOTAL: " +
                                String.format("%.2f €", currentOrder.getTotalPrice())
                );
                alert.showAndWait();
            });

            beverageBox.getChildren().add(btn);
        }
    }

    private String getDescription(String bev) {
        switch (bev) {

            case "Espresso":
                return "A short and intense coffee made with hot water under pressure. "
                        + "Strong flavor and rich aroma.";

            case "Americano":
                return "An espresso diluted with hot water. "
                        + "Lighter and smoother, perfect to sip slowly.";

            case "Cappuccino":
                return "Espresso with steamed milk and milk foam. "
                        + "Creamy, balanced, and very popular.";

            case "Macchiato":
                return "Espresso 'stained' with a small amount of steamed milk or foam. "
                        + "Strong coffee taste with a softer touch.";


            case "Iced Latte":
                return "Cold milk with espresso and ice. "
                        + "Refreshing and creamy, ideal for hot days.";

            case "Cold Brew":
                return "Coffee brewed cold over several hours. "
                        + "Smooth, less acidic, and highly aromatic.";

            case "Iced Americano":
                return "Espresso diluted with cold water and ice. "
                        + "Light and refreshing with a classic coffee taste.";

            case "Iced Mocha":
                return "Iced coffee with milk and chocolate. "
                        + "Sweet, rich, and creamy, perfect for chocolate lovers.";


            case "Green Tea":
                return "Delicate green tea with a light, slightly herbal flavor. "
                        + "A refreshing and light beverage.";

            case "Hot Chocolate":
                return "Thick and creamy hot chocolate. "
                        + "Sweet, comforting, and perfect on cold days.";

            case "Black Tea":
                return "Strong and full-bodied black tea. "
                        + "Can be enjoyed plain or with milk and sugar.";

            default:
                return "Description not available.";
        }
    }

    private int mapBeverageToSelection(String bev) {
        switch (bev) {
            case "Espresso": return 1;
            case "Americano": return 2;
            case "Cappuccino": return 3;
            case "Macchiato": return 4;

            case "Iced Latte": return 5;
            case "Cold Brew": return 6;
            case "Iced Americano": return 7;
            case "Iced Mocha": return 8;

            case "Green Tea": return 9;
            case "Hot Chocolate": return 10;
            case "Black Tea": return 11;

            default:
                throw new IllegalArgumentException("Invalid beverage: " + bev);
        }
    }
}

