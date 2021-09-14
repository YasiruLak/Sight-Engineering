package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Supplier;
import util.EmployeeController;
import util.SupplierController;
import view.tm.SupplierTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SupplierManageViewController {
    public TableView<SupplierTm>tblSupplier;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colSupAddress;
    public TableColumn colSupMobile;
    public TableColumn colSupEmail;
    public TextField txtSupId;
    public TextField txtSupName;
    public TextField txtSupMobile;
    public TextField txtSupAddress;
    public TextField txtSupEmail;
    public TextField txtSupSearch;

    private SupplierController controller = new SupplierController();

    public void initialize(){

        colSupId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSupMobile.setCellValueFactory(new PropertyValueFactory<>("mobil"));
        colSupEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            supplierToTable(controller.getAllSupplier());
        } catch (Exception e) {

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

    public void saveSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(
                txtSupId.getText(),
                txtSupName.getText(),
                txtSupAddress.getText(),
                txtSupMobile.getText(),
                txtSupEmail.getText()
        );
        if(controller.saveSupplier(supplier)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            cancelDetail();
            supplierToTable(controller.getAllSupplier());
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void updateSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(
                txtSupId.getText(),
                txtSupName.getText(),
                txtSupAddress.getText(),
                txtSupMobile.getText(),
                txtSupEmail.getText()
        );
        if(controller.updateSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            cancelDetail();
            supplierToTable(controller.getAllSupplier());
        }else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
    }

    public void deleteSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you suer you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            if (controller.deleteSupplier(txtSupId.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                cancelDetail();
                supplierToTable(controller.getAllSupplier());
            } else {
                System.out.println("done");
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            cancelDetail();
        }
    }

    public void cancelSupplierOnAction(ActionEvent actionEvent) {
        cancelDetail();
    }

    public void supplierSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String supplierId =txtSupSearch.getText();
        Supplier supplier = new SupplierController().getSupplier(supplierId);
        if (supplier==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            setData(supplier);
        }
    }

    public void cancelDetail(){
        txtSupId.clear();
        txtSupName.clear();
        txtSupAddress.clear();
        txtSupMobile.clear();
        txtSupEmail.clear();
        txtSupSearch.clear();
    }

    void setData(Supplier supplier){
        txtSupId.setText(supplier.getId());
        txtSupName.setText(supplier.getName());
        txtSupAddress.setText(supplier.getAddress());
        txtSupMobile.setText(supplier.getMobil());
        txtSupEmail.setText(supplier.getEmail());
    }
}
