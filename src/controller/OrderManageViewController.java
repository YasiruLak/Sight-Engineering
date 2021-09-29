package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Item;
import model.OrderDetail;
import model.Order;
import model.Supplier;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.ItemController;
import util.OrderController;
import util.SupplierController;
import util.ValidationUtil;
import view.tm.StockTm;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

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
    public JFXButton btnCancel;
    public JFXButton btnSave;
    public JFXButton btnAddStock;
    public JFXButton btnRemove;
    public JFXButton btnPrintDetail;

    int stockSelectRowForRemove = -1;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern pricePattern = Pattern.compile("^[0-9]{2,7}[.][0]$");
    Pattern qtyPattern = Pattern.compile("^[0-9]{1,5}$");

    public void initialize(){

        btnAddStock.setDisable(true);
        btnSave.setDisable(true);

        storeValidation();

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

    private void storeValidation() {
        map.put(txtUnitPrice, pricePattern);
        map.put(txtQty, qtyPattern);
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
        btnSave.setDisable(false);
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
                          stockTm.getQty(),
                          stockTm.getUnitPrice()
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
            tblStock.getItems().clear();
            txtTtl.setText("");
            btnSave.setDisable(true);
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
      }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAddStock);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Success").showAndWait();
            }
        }
    }

    public void printDetailOnAction(ActionEvent actionEvent) throws JRException {

        JasperDesign jasperDesign = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/OrderDetailReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(jasperDesign);
        ObservableList<StockTm>items = tblStock.getItems();
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(items.toArray()));
        JasperViewer.viewReport(jasperPrint,false);

    }
}
