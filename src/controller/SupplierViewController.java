package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Supplier;
import util.SupplierController;
import view.tm.SupplierTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierViewController {
    public AnchorPane supplierViewContext;
    public TableView tblSupplier;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colSupAddress;
    public TableColumn colSupMobile;
    public TableColumn colSupEmail;
    public Label lblSupCount;

    private SupplierController controller = new SupplierController();

    public void initialize(){

        colSupId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSupMobile.setCellValueFactory(new PropertyValueFactory<>("mobil"));
        colSupEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            supplierToTable(new SupplierController().getAllSupplier());
            setCount();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public void supplierToTable(ArrayList<Supplier> allSupplier){
        ObservableList<SupplierTm> supplierList = FXCollections.observableArrayList();
        allSupplier.forEach(e -> {
            supplierList.add(new SupplierTm(e.getId(), e.getName(), e.getAddress(),
                    e.getMobil(), e.getEmail()));
        });
        tblSupplier.setItems(supplierList);
    }

    public void goToAddSupplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierManageVIew.fxml");
        Parent load = FXMLLoader.load(resource);
        supplierViewContext.getChildren().clear();
        supplierViewContext.getChildren().add(load);
    }

    public void setCount() throws SQLException, ClassNotFoundException {
        lblSupCount.setText(String.valueOf(new SupplierController().supplierCount()));
    }
}
