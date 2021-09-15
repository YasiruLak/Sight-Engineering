package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AdminMainViewController {
    public JFXButton btnLogOut;
    public AnchorPane adminContext;


    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage closestage = (Stage) btnLogOut.getScene().getWindow();
        closestage.close();

        URL resource = this.getClass().getResource("../view/LandingPg.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.centerOnScreen();
        stage.setTitle("SIGHT Engineering | Yasiru Dahanayaka");
        stage.show();
    }

    public void goToManagerManage(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddManagerView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToVehicleView(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/VehicleView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToSupplierView(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToPaymentView(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PaymentView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToReportView(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ReportView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToEmployeeView(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/EmployeeView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToItemView(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ItemView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToHelpOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/HelpView.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void orderManageView(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/OrderView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToAttendViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AttendanceView.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }
}

