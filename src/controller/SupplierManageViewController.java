package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Supplier;
import util.SupplierController;
import util.ValidationUtil;
import view.tm.SupplierTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

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
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnCancel;

    private SupplierController controller = new SupplierController();

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(S00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,30}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern mobilePattern = Pattern.compile("^[0-9]{3}[-]?[0-9]{7}$");
    Pattern emailPattern = Pattern.compile("^[a-z0-9]{3,}[@](gmail)[.][a-z]{3,}$");

    public void initialize(){

        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        storeValidation();

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

    private void storeValidation(){
        map.put(txtSupId, idPattern);
        map.put(txtSupName, namePattern);
        map.put(txtSupAddress, addressPattern);
        map.put(txtSupMobile, mobilePattern);
        map.put(txtSupEmail, emailPattern);
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
            btnSave.setDisable(true);
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
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
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
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
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
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
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

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Success").showAndWait();
            }
        }
    }
}
