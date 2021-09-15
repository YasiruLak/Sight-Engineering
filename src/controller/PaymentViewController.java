package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class PaymentViewController {
    public AnchorPane paymentContext;

    public void goToManagePaymentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PaymentManage.fxml");
        Parent load = FXMLLoader.load(resource);
        paymentContext.getChildren().clear();
        paymentContext.getChildren().add(load);
    }
}
