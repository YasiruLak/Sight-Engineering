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
import model.Vehicle;
import util.VehicleController;
import view.tm.VehicleTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleViewController extends VehicleManageViewController{
    public AnchorPane vehicleViewContext;
    public TableView tblVehicle;
    public TableColumn colVehicleNo;
    public TableColumn colDescription;
    public TableColumn colVehicleType;
    public Label lblVehicleCount;

    public void initialize(){
        try{

            colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colVehicleType.setCellValueFactory(new PropertyValueFactory<>("type"));

            vehicleToTable(new VehicleController().getAllVehicle());
            setCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void goToVehicleManageOnAction(ActionEvent actionEvent) throws IOException{
        URL resource = getClass().getResource("../view/VehicleManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        vehicleViewContext.getChildren().clear();
        vehicleViewContext.getChildren().add(load);
    }

    private void vehicleToTable(ArrayList<Vehicle> allVehicle){
        ObservableList<VehicleTm> obList = FXCollections.observableArrayList();
        allVehicle.forEach(e ->{
            obList.add(
                    new VehicleTm(e.getVehicleNo(), e.getDescription(), e.getType()));
        });
        tblVehicle.setItems(obList);
    }

    public void setCount() throws SQLException, ClassNotFoundException {
        lblVehicleCount.setText(String.valueOf(new VehicleController().vehicleCount()));
    }

}
