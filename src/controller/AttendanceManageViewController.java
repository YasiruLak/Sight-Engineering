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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Attendance;
import model.Employee;
import model.Item;
import model.Material;
import util.AttendanceController;
import util.EmployeeController;
import util.ItemController;
import util.MaterialController;
import view.tm.EmployeeTm;
import view.tm.ItemDetailTM;
import view.tm.ItemViewTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttendanceManageViewController {

    public TableView<ItemViewTm> tblItemView;
    public TableView<ItemDetailTM> tblItemView2;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colQtyOnHand;
    public TableColumn colSize;
    public Label lblAttendId;
    public JFXTextField txtEmpId;
    public JFXTextField txtEmpName;
    public JFXTextField txtEmpType;
    public JFXTextField txtEmpAge;
    public JFXTextField txtEmpContact;
    public TableColumn colItemId2;
    public TableColumn colItemName2;
    public TableColumn colAction2;
    public TableColumn colSize2;
    public TableColumn colQuantity2;
    public Button btnAdd;
    public TextField txtUpdateQuantity;
    public AnchorPane popUp;
    public JFXTextField txtAttendTime;
    public JFXTextField txtAttendDate;
    public TableColumn colReceiveQty2;

    private ItemDetailTM item;

    public void initialize() {
        try {
            colItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));

            colItemId2.setCellValueFactory(new PropertyValueFactory<>("id"));
            colItemName2.setCellValueFactory(new PropertyValueFactory<>("name"));
            colSize2.setCellValueFactory(new PropertyValueFactory<>("size"));
            colQuantity2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            colAction2.setCellValueFactory(new PropertyValueFactory<>("action"));

            tblItemView.getColumns().setAll(colItemId, colItemName, colSize, colQtyOnHand);
            tblItemView2.getColumns().setAll(colItemId2, colItemName2, colSize2, colQuantity2, colAction2);

            tblItemView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                loadPopUp(newValue);
            });

            loadTableData(new ItemController().getAllItem());
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private void loadTableData(List<Item> items) {
        ObservableList<ItemViewTm> list = FXCollections.observableArrayList();
        for ( Item item : items ) {
            list.add(new ItemViewTm(
                    item.getId(),
                    item.getName(),
                    item.getSize(),
                    item.getQtyOnHand()
            ));
        }
        tblItemView.getItems().setAll(list);

    }

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employeeId = txtEmpId.getText();
        Employee employee = new EmployeeController().getEmployee(employeeId);
        if ( employee == null ) {
            new Alert(Alert.AlertType.ERROR, "Empty Result Set").show();
        } else {

            Attendance attendance = new AttendanceController().getAttendance(employeeId);
            if(attendance.getAttendId() != null ) {
                Material material = new MaterialController().getMaterial(attendance.getAttendId());
                if(material.getaId() !=null){

                }
            }else{

                }

            }
            setData(employee);

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

    public void loadPopUp(ItemViewTm itemViewTm) {
        popUp.setVisible(true);
        item = new ItemDetailTM(
                itemViewTm.getItemCode(),
                itemViewTm.getItemName(),
                itemViewTm.getSize(),
                itemViewTm.getQuantityOnHand(),
                new Button("Action"),
                ""
        );
    }

    ObservableList<ItemDetailTM> list = FXCollections.observableArrayList();
    public void onAdd(ActionEvent actionEvent) {
        boolean updated = false;
        item.setQuantity(txtUpdateQuantity.getText());
        for(ItemDetailTM detail : list){
            if ( detail.getId().equals(item.getId()) ){
                int v = Integer.parseInt(txtUpdateQuantity.getText()) + Integer.parseInt(detail.getQuantity());
                detail.setQuantity(v+"");
                tblItemView2.getItems().setAll(list);
                popUp.setVisible(false);
                updated = true;
            }
        }
        if ( !updated ){
            list.add(item);
            tblItemView2.getItems().setAll(list);

            popUp.setVisible(false);
        }
        txtUpdateQuantity.setText("1");
    }

    public void closeOnAction(ActionEvent actionEvent) {
        popUp.setVisible(false);
    }
}
