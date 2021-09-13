package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Vehicle;
import util.VehicleController;
import view.tm.VehicleTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class VehicleManageViewController {
    public ComboBox<String> cmbType;
    public TableView<VehicleTm>tblVehicle;
    public TableColumn colVehicleNo;
    public TableColumn colDescription;
    public TableColumn colVehicleType;
    public TextField txtVehicleNo;
    public TextField txtDescription;
    public TextField txtSearchVehicleNo;

    private VehicleController controller = new VehicleController();

    public void initialize(){

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

    private void vehicleToTable(ArrayList<Vehicle> allVehicle){
        ObservableList<VehicleTm> obList = FXCollections.observableArrayList();
        allVehicle.forEach(e ->{
            obList.add(
                    new VehicleTm(e.getVehicleNo(), e.getDescription(), e.getType())
            );
            tblVehicle.setItems(obList);
        });
    }

    public void saveVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle v1 = new Vehicle(
                txtVehicleNo.getText(), txtDescription.getText(), cmbType.getValue()
        );
            if (controller.saveVehicle(v1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successes");
                clear();
                vehicleToTable(new VehicleController().getAllVehicle());
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again");
            }
}

    public void searchVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String vehicleNo = txtSearchVehicleNo.getText();
        Vehicle v1 = controller.getVehicle(vehicleNo);
        if(v1 == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else {
            setData(v1);
        }
    }

     void setData(Vehicle v) {
        txtVehicleNo.setText(v.getVehicleNo());
        txtDescription.setText(v.getDescription());
        cmbType.setValue(v.getType());
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle v1 = new Vehicle(
                txtVehicleNo.getText(),
                txtDescription.getText(),
                cmbType.getValue()
        );

        if(controller.updateVehicle(v1)){
            new Alert(Alert.AlertType.INFORMATION,"Updated").show();
            clear();

            vehicleToTable(new VehicleController().getAllVehicle());

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

                vehicleToTable(new VehicleController().getAllVehicle());
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

}
