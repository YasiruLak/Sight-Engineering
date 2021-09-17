package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Employee;
import model.Item;
import util.EmployeeController;
import util.ItemController;
import view.tm.ItemViewTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttendanceManageViewController {

    public TableView<ItemViewTm>tblItemView;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colQtyOnHand;
    public TableColumn colAction;
    public TableColumn colSize;
    public Label lblAttendId;
    public JFXTextField txtEmpId;
    public JFXTextField txtEmpName;
    public JFXTextField txtEmpType;
    public JFXTextField txtEmpAge;
    public JFXTextField txtEmpContact;

    public void initialize(){
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblItemView.getColumns().setAll(colItemId,colItemName,colSize,colQtyOnHand,colAction);

        try {
            loadTableData(new ItemController().getAllItem());
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private void loadTableData(List<Item> items){
        ObservableList<ItemViewTm> list = FXCollections.observableArrayList();
        for (Item item : items) {
            list.add(new ItemViewTm(
                    item.getId(),
                    item.getName(),
                    item.getSize(),
                    item.getQtyOnHand(),
                    new Button("Update")
            ));
        }
        tblItemView.getItems().setAll(list);
        updateBtnAction();
    }
    private void updateBtnAction(){
        for (ItemViewTm item : tblItemView.getItems()) {
            item.getButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        showUpdateForm(item);
                    } catch ( IOException e ) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void showUpdateForm(ItemViewTm item) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("../view/QuantityAddPopUp.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load.load()));
        QuantityAddPopUpController controller = load.getController();
        controller.init(new Item(
                item.getItemCode(),
                item.getItemName(),
                "",
                item.getSize(),
                item.getQuantityOnHand()
        ));
        stage.centerOnScreen();
        stage.show();
    }

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employeeId = txtEmpId.getText();
        Employee employee = new EmployeeController().getEmployee(employeeId);
        if (employee==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            setData(employee);
        }
    }

    void setData(Employee employee) {
        txtEmpName.setText(employee.getName());
        txtEmpAge.setText(employee.getAge());
        txtEmpType.setText(employee.getType());
        txtEmpContact.setText(employee.getContact());
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        txtEmpId.clear();
        txtEmpType.clear();
        txtEmpContact.clear();
        txtEmpAge.clear();
        txtEmpName.clear();
    }
}
