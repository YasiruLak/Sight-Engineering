package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import enums.AttendType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;
import util.AttendanceController;
import util.EmployeeController;
import util.ItemController;
import util.ItemDetailController;
import view.tm.ItemDetailTM;
import view.tm.ItemViewTm;
import view.tm.StockTm;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public TextField txtSearchName;
    public TableColumn colReceive2;
    public AnchorPane popUp1;
    public JFXButton btnAdd2;
    public TextField txtGaveQty;
    public TextField txtReceiveQty;
    public JFXTextField txtAttendData;
    public JFXTextField txtAttendTime;

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
            colReceive2.setCellValueFactory(new PropertyValueFactory<>("receiveQty"));

            tblItemView.getColumns().setAll(colItemId, colItemName, colSize, colQtyOnHand);
            tblItemView2.getColumns().setAll(colItemId2, colItemName2, colSize2, colQuantity2, colReceive2);

            tblItemView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                loadPopUp(newValue);
            });

            tblItemView2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                loadPopUp1(newValue);
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
                    String.valueOf(item.getQtyOnHand())
            ));
        }
        tblItemView.getItems().setAll(list);

    }

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employeeId = txtEmpId.getText();
        Employee employee = new EmployeeController().getEmployee(employeeId);
        if ( employee != null ) {
            Attendance attendance = new AttendanceController().getAttendance(employeeId);
            if ( attendance != null ) {
                txtAttendTime.setText(String.valueOf(attendance.getAttendTime()));
                txtAttendData.setText(String.valueOf(attendance.getAttendDate()));
                ObservableList<ItemDetailTM> itemDetails = FXCollections.observableArrayList();
                List<ItemDetail> itemDetailList = new ItemDetailController().getItemDetail(attendance.getAttendId());
                if ( !itemDetailList.isEmpty() ) {
                    for(ItemDetail itemDetail : itemDetailList){
                        Item item = new ItemController().getItem(itemDetail.getItemCode());

                        itemDetails.add(new ItemDetailTM(
                                item.getId(),
                                item.getName(),
                                item.getSize(),
                                itemDetail.getQty(),
                                0
                        ));
                    }
                    tblItemView2.setItems(itemDetails);
                }
            }
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

    public void loadPopUp(ItemViewTm itemViewTm) {
        popUp.setVisible(true);
        item = new ItemDetailTM(
                itemViewTm.getItemCode(),
                itemViewTm.getItemName(),
                itemViewTm.getSize(),
                Integer.parseInt(itemViewTm.getQuantityOnHand()),
                0
        );
    }

    public void loadPopUp1(ItemDetailTM itemDetailTM) {
        popUp1.setVisible(true);
        item = new ItemDetailTM(
                itemDetailTM.getId(),
                itemDetailTM.getName(),
                itemDetailTM.getSize(),
                itemDetailTM.getQuantity(),
                itemDetailTM.getReceiveQty()
        );
    }

    ObservableList<ItemDetailTM> list = FXCollections.observableArrayList();

    public void onAdd(ActionEvent actionEvent) {
        boolean updated = false;
        item.setQuantity(Integer.parseInt(txtUpdateQuantity.getText()));
        for ( ItemDetailTM detail : list ) {
            if ( detail.getId().equals(item.getId()) ) {
                int v = Integer.parseInt(txtUpdateQuantity.getText())+(detail.getQuantity());
                detail.setQuantity(v+0);
                tblItemView2.getItems().setAll(list);
                popUp.setVisible(false);
                updated = true;
            }
        }
        if ( !updated ) {
            list.add(item);
            tblItemView2.getItems().setAll(list);

            popUp.setVisible(false);
        }
        txtUpdateQuantity.setText("1");
    }

    public void closeOnAction(ActionEvent actionEvent) {
        popUp.setVisible(false);
    }

    public void closeAction(ActionEvent actionEvent) {
        popUp1.setVisible(false);
    }

    public void txtSearchNameOnAction(ActionEvent actionEvent) {

    }

    public void removeOnAction(ActionEvent actionEvent) {

    }

    public void onAdd2(ActionEvent actionEvent) {
    }

    public void saveAttendanceOnAction(ActionEvent actionEvent) {
        List<ItemDetailTM> items1 = tblItemView2.getItems();
        Attendance attendance = new Attendance(
                lblAttendId.getText(),
                txtEmpId.getText(),
                Date.valueOf(LocalDate.now()),
                Time.valueOf(LocalTime.now()),
                null,
                null,
                AttendType.ATTEND.toString()


        );

    }
}
