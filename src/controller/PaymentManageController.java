package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Order;
import model.Payment;
import util.OrderController;
import util.PaymentController;
import util.ValidationUtil;
import view.tm.PaymentTm;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class PaymentManageController {
    public TableView<PaymentTm>tblPayment;
    public TableColumn colPaymentId;
    public TableColumn colOrderId;
    public TableColumn colSupplierId;
    public TableColumn colOrderDate;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colAmount;
    public Label lblPaymentId;
    public TextField txtOrderId;
    public TextField txtSupplierId;
    public TextField txtOrderDate;
    public TextField txtAmount;
    public TextField txtInvoiceNo;
    public ComboBox cmbMethod;
    public TableColumn colPayMethod;
    public TableColumn colInvoiceNo;
    public TextField txtSearch;
    public JFXButton btnSave;
    public JFXButton btnCancel;

    private PaymentController controller = new PaymentController();

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern oIdPattern = Pattern.compile("^(O-)[0-9]{3,4}$");
    Pattern invoicePattern = Pattern.compile("^[0-9]{3,14}$");

    public void initialize(){

        btnSave.setDisable(true);

        storeValidation();

        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPayMethod.setCellValueFactory(new PropertyValueFactory<>("payMethod"));
        colInvoiceNo.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));


        try {
            setPaymentId();
            paymentToTable(controller.getAllPayment());
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }

        cmbMethod.getItems().addAll(
                "Cash On Hand", "Check", "Bank Transfer"
        );
        cmbMethod.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    private void storeValidation(){
            map.put(txtOrderId, oIdPattern);
            map.put(txtInvoiceNo, invoicePattern);
    }

    private void paymentToTable(ArrayList<Payment> allPayment){
        ObservableList<PaymentTm> paymentList = FXCollections.observableArrayList();
        allPayment.forEach(e ->{
            paymentList.add(
                    new PaymentTm(e.getPaymentId(), e.getOrderId(), e.getSupplierId(), e.getOrderDate(), e.getDate(),
                            e.getTime(), e.getAmount(), e.getPayMethod(), e.getInvoiceNo()));
        });
        tblPayment.setItems(paymentList);
    }

    private void setPaymentId() throws SQLException, ClassNotFoundException {
        lblPaymentId.setText(controller.getPaymentId());
    }

    public void payPaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Payment payment = new Payment(
                lblPaymentId.getText(), txtOrderId.getText(), txtSupplierId.getText(), java.sql.Date.valueOf(txtOrderDate.getText()),
                Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Double.parseDouble(txtAmount.getText()),
                cmbMethod.getValue().toString(), txtInvoiceNo.getText()
        );
        if (controller.savePayment(payment)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successes");
            cancelDetail();
            setPaymentId();
            paymentToTable(controller.getAllPayment());
            btnSave.setDisable(true);
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again");
        }
    }

    public void cancelPaymentOnAction(ActionEvent actionEvent) {
        cancelDetail();
    }

    public void getOrderDetailOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = txtOrderId.getText();
        Order order = new OrderController().getOrder(orderId);
        if(order == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
            setOrderData(order);
        }
    }

    void setOrderData(Order order){
        txtOrderId.setText(order.getOrderId());
        txtSupplierId.setText(order.getSupplierId());
        txtOrderDate.setText(String.valueOf(order.getDate()));
        txtAmount.setText(String.valueOf(order.getCost()));
    }

    public void cancelDetail(){

        txtOrderId.clear();
        txtSupplierId.clear();
        txtOrderDate.clear();
        txtAmount.clear();
        cmbMethod.getSelectionModel().clearSelection();
        txtInvoiceNo.clear();
        txtSearch.clear();
    }

    public void searchPaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String paymentId = txtSearch.getText();
        Payment payment = controller.getPayment(paymentId);
        if(payment == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else {
            setPaymentData(payment);
        }
    }

    void setPaymentData(Payment payment) {

        lblPaymentId.setText(payment.getPaymentId());
        txtOrderId.setText(payment.getOrderId());
        txtSupplierId.setText(payment.getSupplierId());
        txtOrderDate.setText(String.valueOf(payment.getOrderDate()));
        txtAmount.setText(String.valueOf(payment.getAmount()));
        cmbMethod.setValue(payment.getPayMethod());
        txtInvoiceNo.setText(payment.getInvoiceNo());

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Success").showAndWait();
            }
        }
    }
}
