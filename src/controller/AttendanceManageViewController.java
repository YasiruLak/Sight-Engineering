package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import enums.AttendType;
import enums.ItemStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;
import util.*;
import view.tm.ItemDetailTM;
import view.tm.ItemViewTm;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public TableColumn colSize2;
    public TableColumn colQuantity2;
    public Button btnAdd;
    public TextField txtUpdateQuantity;
    public AnchorPane popUp;
    public TextField txtSearchName;
    public TableColumn colReceive2;
    public AnchorPane popUp1;
    public JFXButton btnAdd2;
    public TextField txtReceiveQty;
    public JFXTextField txtAttendData;
    public JFXTextField txtAttendTime;

    private ItemDetailTM item;
    private ItemDetail itemDetail;

    private ItemController controller = new ItemController();

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
                loadPopUpGetProduct(newValue);
            });

            setAttendId();
            loadTableData(controller.getAllItem());

        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        popUp1.setVisible(false);
    }

    private void setAttendId() throws SQLException, ClassNotFoundException {

        String attendanceId = new AttendanceController().getAttendanceId();
        lblAttendId.setText(attendanceId);
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
                lblAttendId.setText(attendance.getAttendId());
                txtAttendTime.setText(String.valueOf(attendance.getAttendTime()));
                txtAttendData.setText(String.valueOf(attendance.getAttendDate()));
                loadPending(attendance.getAttendId());

            } else {
                setAttendId();
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
        clearDetail();
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

    public void loadPopUpGetProduct(ItemDetailTM itemDetailTM) {
        if ( itemDetailTM != null ){
            popUp1.setVisible(true);

            this.itemDetail = new ItemDetail();
            this.itemDetail.setItemCode(itemDetailTM.getId());
            this.itemDetail.setQty(itemDetailTM.getQuantity());
            this.itemDetail.setAttendId(lblAttendId.getText());
        }
    }

    ObservableList<ItemDetailTM> list = FXCollections.observableArrayList();

    public void onAdd(ActionEvent actionEvent) {
        boolean updated = false;
        item.setQuantity(Integer.parseInt(txtUpdateQuantity.getText()));
        for ( ItemDetailTM detail : list ) {
            if ( detail.getId().equals(item.getId()) ) {
                int v = Integer.parseInt(txtUpdateQuantity.getText()) + (detail.getQuantity());
                detail.setQuantity(v);
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

    public void saveAttendanceOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtAttendTime.getText().equals("")) {
            Attendance attendance = new Attendance(
                    lblAttendId.getText(),
                    txtEmpId.getText(),
                    Date.valueOf(LocalDate.now()),
                    Time.valueOf(LocalTime.now()),
                    null,
                    null,
                    AttendType.ATTEND.toString()
            );

            new AttendanceController().saveAttendance(attendance);

            List<ItemDetailTM> items1 = tblItemView2.getItems();
            for ( ItemDetailTM itemDetailTM : items1 ) {
                ItemDetail itemDetail = new ItemDetail();
                itemDetail.setItemCode(itemDetailTM.getId());
                itemDetail.setAttendId(lblAttendId.getText());
                itemDetail.setQty(itemDetailTM.getQuantity());
                itemDetail.setStatus(ItemStatus.PENDING.toString());
                itemDetail.setReceiveQty(itemDetailTM.getReceiveQty());

                new ItemDetailController().saveItemDetail(itemDetail);

                Item item = controller.getItem(itemDetail.getItemCode());

                int qtyOnHand = item.getQtyOnHand() - itemDetailTM.getQuantity();

                item.setQtyOnHand(qtyOnHand);
                controller.updateItem(item);

            }
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            clearDetail();
            setAttendId();
            tblItemView2.getItems().clear();
            loadTableData(controller.getAllItem());

        }else {
            if ( tblItemView2.getItems().isEmpty() ){
                Attendance attendance = new Attendance(
                        lblAttendId.getText(),
                        txtEmpId.getText(),
                        Date.valueOf(txtAttendData.getText()),
                        Time.valueOf(txtAttendTime.getText()),
                        Date.valueOf(LocalDate.now()),
                        Time.valueOf(LocalTime.now()),
                        AttendType.OUT.toString()

                );
                new AttendanceController().updateAttendance(attendance);
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
                tblItemView2.getItems().clear();
            }else{
                new Alert(Alert.AlertType.WARNING, "Settle Material Detail").show();
            }

        }



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

    public void saveData(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int receiveQty = Integer.parseInt(txtReceiveQty.getText());
        if ( receiveQty > this.itemDetail.getQty() ) {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            return;
        }
        this.itemDetail.setReceiveQty(receiveQty);
        this.itemDetail.setStatus(ItemStatus.RECEIVED.toString());
        this.itemDetail.setQty(this.itemDetail.getQty() - receiveQty);

        boolean b = new ItemDetailController().updateItemDetail(itemDetail);
        if ( b ) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            loadPending(lblAttendId.getText());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        popUp1.setVisible(false);

    }

    public void clearDetail() {
        txtEmpId.clear();
        txtEmpType.clear();
        txtEmpContact.clear();
        txtEmpAge.clear();
        txtEmpName.clear();
        txtAttendTime.clear();
        txtAttendData.clear();
        tblItemView2.refresh();
    }

    private void loadPending(String attendanceId) throws SQLException, ClassNotFoundException {
        ObservableList<ItemDetailTM> itemDetails = FXCollections.observableArrayList();
        List<ItemDetail> itemDetailList = new ItemDetailController().getItemDetail(attendanceId);
        if ( !itemDetailList.isEmpty() ) {
            for ( ItemDetail itemDetail : itemDetailList ) {
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
        }else {
            tblItemView2.setItems(FXCollections.observableArrayList());
        }

    }

}
