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
import model.OrderDetail;
import model.Order;
import model.Supplier;
import util.ItemController;
import util.OrderController;
import util.SupplierController;
import view.tm.StockTm;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrderManageViewController {
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
    public TableColumn colQty;;
    public TableView tblStock;
    public TableColumn colTotal;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TextField txtQty;
    public TextField txtUnitPrice;

    int stockSelectRowForRemove = -1;

    public void initialize(){

        colItmCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


        try {
            setOrderId();
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

    private void setOrderId() throws SQLException, ClassNotFoundException {
        lblOrderId.setText(new OrderController().getOrderId());
    }

    private void setItemCode(String itemCode) throws SQLException, ClassNotFoundException {
        Item item = new ItemController().getItem(itemCode);
        if(item==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        }else{
            txtItmName.setText(item.getName());
            txtItmDescription.setText(item.getDescription());
            txtItmSize.setText(item.getSize());
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
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

        itemClear();

        cmbSupplierId.setDisable(true);
        txtSupName.setDisable(true);
        txtSupAddress.setDisable(true);
        txtSupContact.setDisable(true);
        txtSupEmail.setDisable(true);

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

    public void saveOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetail> items = new ArrayList<>();

        double total = 0;

        for(StockTm stockTm: stockList
        ){
            total+=stockTm.getTotal();
          items.add(
                  new OrderDetail(
                          stockTm.getItemCode(),
                          stockTm.getUnitPrice(),
                          stockTm.getQty()
                  )
          );
        }

        Order order = new Order(
                lblOrderId.getText(),
                cmbSupplierId.getValue(),
                Date.valueOf(LocalDate.now()),
                Time.valueOf(LocalTime.now()),
                total,
                items
        );
        if(new OrderController().saveOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
            clear();
            enable();
        }else{
            new Alert(Alert.AlertType.ERROR , "Try Again").show();
        }
        
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
            enable();
            clear();
            tblStock.refresh();

        }
    }

    public void enable(){
        cmbSupplierId.setDisable(false);
        txtSupName.setDisable(false);
        txtSupAddress.setDisable(false);
        txtSupContact.setDisable(false);
        txtSupEmail.setDisable(false);
    }

     public void itemClear() {
         cmbItemCode.getSelectionModel().clearSelection();
         txtItmName.clear();
         txtItmDescription.clear();
         txtItmSize.clear();
         txtQtyOnHand.clear();
         txtUnitPrice.clear();
         txtQty.clear();
     }
}
