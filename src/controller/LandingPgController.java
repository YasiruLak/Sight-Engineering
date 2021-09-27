package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class LandingPgController{
    public JFXPasswordField txtPassword;
    public JFXTextField txtUserName;
    public JFXButton btnLogIn;
    public JFXButton btnExit;

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        String user = txtUserName.getText();
        String password = txtPassword.getText();

        if (user.equals("a")) {

            Stage logStage = (Stage) btnLogIn.getScene().getWindow();
            logStage.close();

            URL resource = this.getClass().getResource("../view/AdminMainView.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        } else if (user.equals("m")) {

            Stage logStage = (Stage) btnLogIn.getScene().getWindow();
            logStage.close();

            URL resource = this.getClass().getResource("../view/ManagerMainView.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid User Name Or Password ! ").show();
            txtUserName.clear();
            txtPassword.clear();
        }

    }

    public void exitOnAction(ActionEvent actionEvent){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();

//        try {
//            Thread.sleep(5);
//            Alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("| SIGHT Engineering | ");
//            alert.setHeaderText(null);
//            alert.setContentText("Good Bye Sir...!");
//            alert.showAndWait();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        btnLogIn.requestLayout();

    }

    public void userNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}
