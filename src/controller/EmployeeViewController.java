package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Employee;
import util.EmployeeController;
import view.tm.EmployeeTm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class EmployeeViewController {
    public AnchorPane employeeContext;
    public TableView<EmployeeTm>tblEmployee;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpType;
    public TableColumn colEmpAge;
    public TableColumn colEmpAddress;
    public TableColumn colEmpCity;
    public TableColumn colEmpProvince;
    public TableColumn colEmpContact;
    public TableColumn colEmpSalary;

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

            employeeToTable(new EmployeeController().getAllEmployee());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void employeeToTable(ArrayList<Employee> allEmployee){
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        allEmployee.forEach(e -> {
            obList.add(new EmployeeTm(e.getId(),e.getName(),e.getType(),e.getAge(),e.getAddress(),e.getCity(),
                    e.getProvince(),e.getContact(),e.getDailySalary()));

        });
        tblEmployee.setItems(obList);
    }

    public void goToAddEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/EmployeManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        employeeContext.getChildren().clear();
        employeeContext.getChildren().add(load);
    }
}
