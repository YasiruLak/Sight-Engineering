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
import model.Order;
import util.OrderController;
import view.tm.OrderTm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderViewController {
    public AnchorPane OrderContext;
    public TableView tblOrder;
    public TableColumn colOrderId;
    public TableColumn colSupplierId;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colTotal;
    public Label lblOrderCount;
    public TableView tblOrderView2;
    public TableColumn colOrderItemCode;
    public TableColumn colOrderItemQty;
    public TableColumn colOrderPrice;

    public void initialize(){

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("cost"));

        try {
            orderToTable(new OrderController().getAllOrder());
            setCount();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loadOrderViewTable(newValue);
        });
    }

    private void loadOrderViewTable(Object newValue) {

    }

    public void orderToTable(ArrayList<Order> allOrder){
        ObservableList<OrderTm> orderList = FXCollections.observableArrayList();
        allOrder.forEach(e -> {
            orderList.add(new OrderTm(e.getOrderId(), e.getSupplierId(), e.getDate(),
                    e.getTime(), e.getCost()));
        });
        tblOrder.setItems(orderList);
    }

    public void goToOrderManageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/OrderManageView.fxml");
        Parent load = FXMLLoader.load(resource);
        OrderContext.getChildren().clear();
        OrderContext.getChildren().add(load);
    }

    public void setCount() throws SQLException, ClassNotFoundException {
        lblOrderCount.setText(String.valueOf(new OrderController().orderCount()));
    }
}
