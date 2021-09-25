package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetail;
import util.OrderController;
import view.tm.OrderDetailTm;

import java.sql.SQLException;

public class OrderDetailViewController {
    public TableView<OrderDetailTm>tblOrderDetail;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public Label lblOrderId;
    public Label lblTotalCost;

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

    }

    public void loadAllData(String id) throws SQLException, ClassNotFoundException {
        lblOrderId.setText(id);
        double total = 0;
        ObservableList<OrderDetailTm> tmList = FXCollections.observableArrayList();

        for( OrderDetail orderDetail: new OrderController().getAllOrderDetails(id)
        ){
            total=+orderDetail.getUnitPrice();
            tmList.add(new OrderDetailTm(orderDetail.getItemCode(), orderDetail.getUnitPrice(), orderDetail.getQtyForBuy()));
        }
        tblOrderDetail.setItems(tmList);
        lblTotalCost.setText("Rs "+total+"/=");

    }


}
