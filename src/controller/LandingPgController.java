package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Login;
import util.LoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;

public class LandingPgController {
    public JFXPasswordField txtPassword;
    public JFXTextField txtUserName;
    public JFXButton btnLogIn;
    public JFXButton btnExit;

    public void logInOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String userName = txtUserName.getText();
        String password = new String(Base64.getEncoder().encode(txtPassword.getText().getBytes()));

        Login login = new LoginController().getUser(userName, password);
        if ( login != null ) {
            if ( login.getRole().equals("MANAGER") ) {

                Stage logStage = (Stage) btnLogIn.getScene().getWindow();
                logStage.close();

                URL resource = this.getClass().getResource("../view/ManagerMainView.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            } else if ( login.getRole().equals("ADMIN") ) {

                Stage logStage = (Stage) btnLogIn.getScene().getWindow();
                logStage.close();

                URL resource = this.getClass().getResource("../view/AdminMainView.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            }
        }
        txtUserName.clear();
        txtPassword.clear();
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        btnLogIn.requestLayout();
    }

    public void userNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void exitOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
