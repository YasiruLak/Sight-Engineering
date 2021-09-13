package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SupplierViewController {
    public AnchorPane supplierViewContext;
    public TableView tblSupplier;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colSupAddress;
    public TableColumn colSupMobile;
    public TableColumn colSupEmail;

    public void goToAddSupplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierManageVIew.fxml");
        Parent load = FXMLLoader.load(resource);
        supplierViewContext.getChildren().clear();
        supplierViewContext.getChildren().add(load);
    }
}
