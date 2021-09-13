package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
import model.Item;
import model.Supplier;
import util.ItemController;
import util.SupplierController;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class OrderManageViewController {
    public Label txtDate;
    public Label txtTime;
    public ComboBox<String> cmbItemCode;
    public ComboBox<String> cmbSupplierId;
    public Label lblOrderId;
    public Label txtTtl;
    public JFXTextField txtItmName;
    public JFXTextField txtSupName;
    public JFXTextField txtSupContact;
    public JFXTextField txtItmDescription;
    public JFXTextField txtItmSize;
    public JFXTextField txtQty;
    public JFXTextField txtSupAddress;
    public JFXTextField txtSupEmail;
    public JFXTextField txtQtyOnHand;
    public TableColumn colItmCode;
    public TableColumn colItmName;
    public TableColumn colQty;
    public TableColumn colUpdate;
    public TableColumn colDelete;
    public TableView tblStock;
    public TableColumn colTotal;
    public JFXTextField txtUnitPrice;
    private int hour;

    public void initialize(){
        loadDateAndTime();
        try {
            loadSupplierIds();
            loadItemCodes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSupplierId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setSupplierData(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemCode(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void setItemCode(String itemCode) throws SQLException, ClassNotFoundException {
        Item item = new ItemController().getItem(itemCode);
        if(item==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        }else{
            txtItmName.setText(item.getName());
            txtItmDescription.setText(item.getDescription());
            txtItmSize.setText(item.getSize());
            txtQtyOnHand.setText(item.getQtyOnHand());
        }
    }

    private void setSupplierData(String supplierId) throws SQLException, ClassNotFoundException {
        Supplier supplier = new SupplierController().getSupplier(supplierId);
        if(supplier==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        }else{
            txtSupName.setText(supplier.getName());
            txtSupAddress.setText(supplier.getAddress());
            txtSupContact.setText(supplier.getMobil());
            txtSupEmail.setText(supplier.getEmail());
        }
    }

    private void loadSupplierIds() throws SQLException, ClassNotFoundException {
        List<String> supplierId = new SupplierController().getSupplierId();
        cmbSupplierId.getItems().addAll(supplierId);
    }

    private void loadItemCodes() throws SQLException, ClassNotFoundException {
        List<String> itemCode = new ItemController().getItemCodes();
        cmbItemCode.getItems().addAll(itemCode);

    }

    public void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            String state = null;
            hour = currentTime.getHour();
            if (hour < 12) {
                state = "AM";
            } else {
                state = "PM";
            }
            txtTime.setText(
                    currentTime.getHour()+ ": "+currentTime.getMinute()+ ": "+state
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void cancelOnAction(ActionEvent actionEvent) {
    }

    public void addToStockOnAction(ActionEvent actionEvent) {

    }

    public void saveOrderOnAction(ActionEvent actionEvent) {
    }
}
