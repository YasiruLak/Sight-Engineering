package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Payment;
import util.PaymentController;
import view.tm.PaymentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentViewController {
    public AnchorPane paymentContext;
    public Label lblPaymentCount;
    public TableView tblPayment;
    public TableColumn colPaymentId;
    public TableColumn colOrderId;
    public TableColumn colSupplierId;
    public TableColumn colOrderDate;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colAmount;
    public TableColumn colPayMethod;
    public TableColumn colInvoiceNo;

    public void initialize(){

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
            paymentToTable(new PaymentController().getAllPayment());
            setCount();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
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

    public void setCount() throws SQLException, ClassNotFoundException {
        lblPaymentCount.setText(String.valueOf(new PaymentController().paymentCount()));
    }

    public void goToManagePaymentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PaymentManage.fxml");
        Parent load = FXMLLoader.load(resource);
        paymentContext.getChildren().clear();
        paymentContext.getChildren().add(load);
    }
}
