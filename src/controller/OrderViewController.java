package controller;

import com.jfoenix.controls.JFXButton;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.OrderView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.OrderController;
import view.tm.OrderTm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class OrderViewController {
    public AnchorPane OrderContext;
    public TableView<OrderTm>tblOrder;
    public TableColumn colOrderId;
    public Label lblOrderCount;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public TableColumn colTotalCost;
    public JFXButton btnSupWiseOrder;

    public void initialize(){

        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        try {
            loadAllData();
            setCount();
        } catch ( Exception e ) {

        }

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadDetailsUi(newValue.getOrderId());
            } catch ( IOException e ) {
                e.printStackTrace();
            } catch ( SQLException e ) {
                e.printStackTrace();
            } catch ( ClassNotFoundException e ) {
                e.printStackTrace();
            }
        });
    }

    private void loadDetailsUi(String orderId) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/OrderDetailView.fxml"));
        Parent load = fxmlLoader.load();
        OrderDetailViewController controller = fxmlLoader.getController();
        controller.loadAllData(orderId);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setTitle("Order Details Table");
        stage.setScene(scene);
        stage.show();
    }


    private void loadAllData() throws SQLException, ClassNotFoundException {
        ObservableList<OrderTm> tmList = FXCollections.observableArrayList();
        for( OrderView tempOrder: new OrderController().getAllOrders()
        ){
            tmList.add(
                    new OrderTm(tempOrder.getSupplierId(), tempOrder.getSupplierName(), tempOrder.getOrderId(),
                            tempOrder.getDate(),tempOrder.getTime(), tempOrder.getTotalCost())
            );
        }
        tblOrder.setItems(tmList);
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


    public void supplierWiseOrderChart(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/SupplierWiseOrder.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }
}
