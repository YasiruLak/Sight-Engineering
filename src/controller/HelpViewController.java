package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Employee;
import view.tm.EmployeeTm;

import java.util.ArrayList;

public class HelpViewController {

    public TableView tblHelp;
    public TableColumn colTxtFieldName;
    public TableColumn colTxtType;

//    public void validationToTable(ArrayList<Employee> allEmployee) {
//        ObservableList<EmployeeTm> employeeList = FXCollections.observableArrayList();
//        allEmployee.forEach(e -> {
//            employeeList.add(new EmployeeTm(e.getId(), e.getName(), e.getType(), e.getAge(), e.getAddress(), e.getCity(),
//                    e.getProvince(), e.getContact(), e.getDailySalary()));
//
//        });
//        tblEmployee.setItems(employeeList);
//    }
}
