package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class OrderViewController {
    public AnchorPane OrderContext;

    public void goToOrderManageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/OrderManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        OrderContext.getChildren().clear();
        OrderContext.getChildren().add(load);
    }
}
