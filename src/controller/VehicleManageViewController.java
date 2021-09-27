package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Vehicle;
import util.ValidationUtil;
import util.VehicleController;
import view.tm.VehicleTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class VehicleManageViewController {
    public ComboBox<String> cmbType;
    public TableView<VehicleTm>tblVehicle;
    public TableColumn colVehicleNo;
    public TableColumn colDescription;
    public TableColumn colVehicleType;
    public TextField txtVehicleNo;
    public TextField txtDescription;
    public TextField txtSearchVehicleNo;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnCancel;

    private VehicleController controller = new VehicleController();

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern codePattern = Pattern.compile("^[A-Z0-9]{2,3}[-][0-9]{4}$");
    Pattern descPattern = Pattern.compile("^[A-z ]{3,50}$");

    public void initialize(){

        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        //cancelBtn();

        storeValidations();

        try {

        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("type"));



            vehicleToTable(controller.getAllVehicle());
        } catch (Exception e) {
            e.printStackTrace();
        }


        cmbType.getItems().addAll(
                "Van", "Bus", "Double Cab", "Lorry", "Bulldozer", "Excavators", "Cranes", "Ten Wheals",
                "Car","Bike"
        );
        cmbType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        });
    }

    private void storeValidations() {
        map.put(txtVehicleNo, codePattern);
        map.put(txtDescription, descPattern);
    }

    private void vehicleToTable(ArrayList<Vehicle> allVehicle){
        ObservableList<VehicleTm> vehicleList = FXCollections.observableArrayList();
        allVehicle.forEach(e ->{
            vehicleList.add(
                    new VehicleTm(e.getVehicleNo(), e.getDescription(), e.getType()));
        });
        tblVehicle.setItems(vehicleList);
    }

    public void saveVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = new Vehicle(
                txtVehicleNo.getText(), txtDescription.getText(), cmbType.getValue()
        );
            if (controller.saveVehicle(vehicle)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successes");
                clear();
                vehicleToTable(controller.getAllVehicle());
                btnSave.setDisable(true);
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again");
            }
}

    public void searchVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String vehicleNo = txtSearchVehicleNo.getText();
        Vehicle vehicle = controller.getVehicle(vehicleNo);
        if(vehicle == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else {
            setData(vehicle);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

     void setData(Vehicle v) {
        txtVehicleNo.setText(v.getVehicleNo());
        txtDescription.setText(v.getDescription());
        cmbType.setValue(v.getType());
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = new Vehicle(
                txtVehicleNo.getText(),
                txtDescription.getText(),
                cmbType.getValue()
        );

        if(controller.updateVehicle(vehicle)){
            new Alert(Alert.AlertType.INFORMATION,"Updated").show();
            clear();
            vehicleToTable(controller.getAllVehicle());
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

        }else{
            new Alert(Alert.AlertType.ERROR,"Try Again").show();
        }
    }

    public void deleteVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,"Are you suer you want to Delete?",yes,no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no)==yes){
            if(controller.deleteVehicle(txtVehicleNo.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                clear();
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
                vehicleToTable(controller.getAllVehicle());

                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
                clear();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void clear(){
        txtVehicleNo.clear();
        txtDescription.clear();
        cmbType.getSelectionModel().clearSelection();
        txtSearchVehicleNo.clear();

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

//    private void cancelBtn(){
//        if(txtVehicleNo.getText().isEmpty()){
//            btnCancel.setDisable(true);
//        }else{
//            btnCancel.setDisable(false);
//        }
//    }
}
