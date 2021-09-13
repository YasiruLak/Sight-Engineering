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

public class ManagerMainViewController {
    public JFXButton btnLogOut;
    public AnchorPane managerContext;


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

    public void goToItemManageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ItemManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        managerContext.getChildren().clear();
        managerContext.getChildren().add(load);
    }

    public void goToVehicleOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/VehicleManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        managerContext.getChildren().clear();
        managerContext.getChildren().add(load);
    }

    public void goToSupplierManage(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierManageVIew.fxml");
        Parent load = FXMLLoader.load(resource);
        managerContext.getChildren().clear();
        managerContext.getChildren().add(load);
    }

    public void loadPayementOnAction(ActionEvent actionEvent) {
    }

    public void goToReportOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ReportView.fxml");
        Parent load = FXMLLoader.load(resource);
        managerContext.getChildren().clear();
        managerContext.getChildren().add(load);
    }

    public void goToEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/EmployeManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        managerContext.getChildren().clear();
        managerContext.getChildren().add(load);
    }

    public void goToHelpOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/HelpView.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void goToOrderManageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/OrderManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        managerContext.getChildren().clear();
        managerContext.getChildren().add(load);
    }

    public void goToAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AttendanceManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        managerContext.getChildren().clear();
        managerContext.getChildren().add(load);
    }
}
