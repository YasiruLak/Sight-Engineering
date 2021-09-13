package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Item;
import model.Supplier;
import util.ItemController;
import util.SupplierController;
import view.tm.StockTm;

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
    public JFXTextField txtQty;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    private int hour;

    int stockSelectRowForRemove = -1;

    public void initialize(){

        colItmCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


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

        tblStock.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            stockSelectRowForRemove = (int) newValue;
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
            clear();

        }

    ObservableList<StockTm> stockList = FXCollections.observableArrayList();

    public void addToStockOnAction(ActionEvent actionEvent) {
        String itemCode = cmbItemCode.getSelectionModel().getSelectedItem();
        String name = txtItmName.getText();
        String description = txtItmDescription.getText();
        int quantity = Integer.parseInt(txtQty.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = quantity * unitPrice;


        StockTm tm = new StockTm(
                itemCode,
                name,
                description,
                quantity,
                unitPrice,
                total
        );

        int rowNumber = isExist(tm);

        if(isExist(tm)==-1){
            stockList.add(tm);
        }else{
            StockTm temp = stockList.get(rowNumber);
            StockTm newTm =new StockTm(
                    temp.getItemCode(),
                    temp.getName(),
                    temp.getDescription(),
                    temp.getQty()+quantity,
                    unitPrice,
                    total+temp.getTotal()
            );
            stockList.remove(rowNumber);
            stockList.add(newTm);
        }

        tblStock.setItems(stockList);

        txtUnitPrice.clear();
        txtQty.clear();

        calculateCost();
    }

    private int isExist(StockTm tm){

        for ( int i = 0; i < stockList.size(); i++ ) {
            if(tm.getItemCode().equals(stockList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }



    public void saveOrderOnAction(ActionEvent actionEvent) {

    }

    public void clear(){
        cmbSupplierId.getSelectionModel().clearSelection();
        txtSupName.clear();
        txtSupAddress.clear();
        txtSupContact.clear();
        txtSupEmail.clear();
        cmbItemCode.getSelectionModel().clearSelection();
        txtItmName.clear();
        txtItmDescription.clear();
        txtItmSize.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        txtQty.clear();
    }

    void calculateCost(){
        double total = 0;
        for(StockTm stockTm: stockList
        ){
            total+=stockTm.getTotal();
        }
        txtTtl.setText(total+" /=");
    }

    public void removeOnAction(ActionEvent actionEvent) {
        if ( stockSelectRowForRemove == -1 ) {
            new Alert(Alert.AlertType.WARNING, "Please Select Row").show();
        } else {
            stockList.remove(stockSelectRowForRemove);
            calculateCost();
            tblStock.refresh();
        }
    }
}
