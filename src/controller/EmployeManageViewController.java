package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import util.EmployeeController;
import view.tm.EmployeeTm;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EmployeManageViewController {
    public ComboBox cmbEmpType;
    public TableView<EmployeeTm> tblEmployee;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpType;
    public TableColumn colEmpAge;
    public TableColumn colEmpAddress;
    public TableColumn colEmpCity;
    public TableColumn colEmpProvince;
    public TableColumn colEmpContact;
    public TableColumn colEmpSalary;
    public ComboBox cmbEmpProvince;
    public TextField txtEmpId;
    public TextField txtEmpName;
    public TextField txtEmpAge;
    public TextField txtEmpAddress;
    public TextField txtEmpCity;
    public TextField txtEmpMobile;
    public TextField txtEmpSearch;
    public TextField txtEmpDailySalary;

    private EmployeeController controller = new EmployeeController();

    public void initialize(){

        try {

            colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colEmpType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colEmpAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colEmpCity.setCellValueFactory(new PropertyValueFactory<>("city"));
            colEmpProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
            colEmpContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colEmpSalary.setCellValueFactory(new PropertyValueFactory<>("dailySalary"));

            employeeToTable(controller.getAllEmployee());

        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbEmpType.getItems().addAll(
                "Supervisor", "Carpenter", "Mason", "Worker", "Plumber", "Hand helper", "Driver", "Stock keeper"
        );
        cmbEmpType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
        cmbEmpProvince.getItems().addAll(
                "Central", "Eastern", "Southern", "Western", "Northern", "North Western", "North Central",
                "Uva", "Sabaragamuwa"
        );
        cmbEmpProvince.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    public void employeeToTable(ArrayList<Employee> allEmployee) {
        ObservableList<EmployeeTm> employeeList = FXCollections.observableArrayList();
        allEmployee.forEach(e -> {
            employeeList.add(new EmployeeTm(e.getId(), e.getName(), e.getType(), e.getAge(), e.getAddress(), e.getCity(),
                    e.getProvince(), e.getContact(), e.getDailySalary()));

        });
        tblEmployee.setItems(employeeList);
    }

    public void saveEmpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee(
                txtEmpId.getText(),
                txtEmpName.getText(),
                cmbEmpType.getValue().toString(),
                txtEmpAge.getText(),
                txtEmpAddress.getText(),
                txtEmpCity.getText(),
                cmbEmpProvince.getValue().toString(),
                txtEmpMobile.getText(),
                txtEmpDailySalary.getText()
        );
        if (controller.saveEmployee(employee)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

            cancelDetail();

            employeeToTable(controller.getAllEmployee());

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void updateEmpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee(
                txtEmpId.getText(),
                txtEmpName.getText(),
                cmbEmpType.getValue().toString(),
                txtEmpAge.getText(),
                txtEmpAddress.getText(),
                txtEmpCity.getText(),
                cmbEmpProvince.getValue().toString(),
                txtEmpMobile.getText(),
                txtEmpDailySalary.getText()
        );

        if (controller.updateEmployee(employee)) {
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            cancelDetail();
            employeeToTable(controller.getAllEmployee());
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
    }

    public void deleteEmpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you suer you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            if (controller.deleteEmployee(txtEmpId.getText())){
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                cancelDetail();
                employeeToTable(controller.getAllEmployee());
            }else{
                System.out.println("done");
            }
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            cancelDetail();
        }
    }

    public void cancelDetailOnAction(ActionEvent actionEvent) {
        cancelDetail();

    }

    public void empSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employeeId =txtEmpSearch.getText();
        Employee employee = new EmployeeController().getEmployee(employeeId);
        if (employee==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            setData(employee);
        }
    }

    void setData(Employee employee) {
        txtEmpId.setText(employee.getId());
        txtEmpName.setText(employee.getName());
        cmbEmpType.setValue(employee.getType());
        txtEmpAge.setText(employee.getAge());
        txtEmpAddress.setText(employee.getAddress());
        txtEmpCity.setText(employee.getCity());
        cmbEmpProvince.setValue(employee.getProvince());
        txtEmpMobile.setText(employee.getContact());
        txtEmpDailySalary.setText(employee.getDailySalary());

    }

    public void cancelDetail(){
        txtEmpId.clear();
        txtEmpName.clear();
        cmbEmpType.getSelectionModel().clearSelection();
        txtEmpAge.clear();
        txtEmpAddress.clear();
        txtEmpCity.clear();
        cmbEmpProvince.getSelectionModel().clearSelection();
        txtEmpMobile.clear();
        txtEmpDailySalary.clear();
        txtEmpSearch.clear();
    }
}
