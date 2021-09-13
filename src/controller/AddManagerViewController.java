package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Manager;
import util.ManagerController;
import view.tm.ManagerTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddManagerViewController {
    public ComboBox cmbMngStatus;
    public TableView<ManagerTm>tblManager;
    public TableColumn colNic;
    public TableColumn colName;
    public TableColumn colStatus;
    public TableColumn colAge;
    public TableColumn colAddress;
    public TableColumn colMobile;
    public TableColumn colEmail;
    public TextField txtMngNic;
    public TextField txtMngName;
    public TextField txtMngAge;
    public TextField txtMngAddress;
    public TextField txtMngMobile;
    public TextField txtMngEmail;
    public ComboBox cmbManagerNic;
    public AnchorPane managerContext;

    private ManagerController controller = new ManagerController();

    public void initialize() {

        try {

        colNic.setCellValueFactory(new PropertyValueFactory<>("nicNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            managerToTable(controller.getAllManager());
            loadManagerNic();

        } catch (Exception e){

    }
        cmbManagerNic.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setManagerData((String) newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
        cmbMngStatus.getItems().addAll(
                "Married", "Unmarried"
        );
        cmbMngStatus.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });

    }
    public void managerToTable(ArrayList<Manager> allManager) {
        ObservableList<ManagerTm> managerList = FXCollections.observableArrayList();
        allManager.forEach(e -> {
            managerList.add(new ManagerTm(e.getNicNo(), e.getName(), e.getStatus(), e.getAge(), e.getAddress(), e.getMobile(),
                    e.getEmail())
            );
            tblManager.setItems(managerList);
        });
    }

    private void loadManagerNic() throws SQLException, ClassNotFoundException {
        List<String> managerNic = new ManagerController().getManagerNic();
        cmbManagerNic.getItems().addAll(managerNic);

    }

    public void saveManagerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Manager manager = new Manager(
                txtMngNic.getText(),
                txtMngName.getText(),
                cmbMngStatus.getValue().toString(),
                txtMngAge.getText(),
                txtMngAddress.getText(),
                txtMngMobile.getText(),
                txtMngEmail.getText()
        );
        if(controller.saveManager(manager)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            clearDetail();
            managerToTable(controller.getAllManager());

        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    private void setManagerData(String nic) throws SQLException, ClassNotFoundException {
        Manager manager = new ManagerController().getManager(nic);
        if (manager == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtMngNic.setText(manager.getNicNo());
            txtMngName.setText(manager.getName());
            cmbMngStatus.setValue(manager.getStatus());
            txtMngAge.setText(manager.getAge());
            txtMngAddress.setText(manager.getAddress());
            txtMngMobile.setText(manager.getMobile());
            txtMngEmail.setText(manager.getEmail());

        }
    }

    public void updateManagerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Manager manager = new Manager(
                txtMngNic.getText(),
                txtMngName.getText(),
                cmbMngStatus.getValue().toString(),
                txtMngAge.getText(),
                txtMngAddress.getText(),
                txtMngMobile.getText(),
                txtMngEmail.getText()
        );
        if (controller.updateManager(manager)) {
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            clearDetail();
            managerToTable(controller.getAllManager());
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
    }

    public void deleteManagerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you suer you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            if (controller.deleteManager(txtMngNic.getText()))
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                clearDetail();
                managerToTable(controller.getAllManager());
        }else {
                clearDetail();
        }
    }


    public void cancelManagerOnAction(ActionEvent actionEvent) {
        clearDetail();
    }
    public void clearDetail(){
        txtMngNic.clear();
        txtMngName.clear();
        cmbMngStatus.getSelectionModel().clearSelection();
        txtMngAge.clear();
        txtMngAddress.clear();
        txtMngEmail.clear();
        txtMngMobile.clear();
        cmbManagerNic.getSelectionModel().clearSelection();
    }

}
