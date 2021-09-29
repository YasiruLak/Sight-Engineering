package controller;

import com.jfoenix.controls.JFXButton;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;

public class ReportViewController {
    public BarChart attendanceLineChart;
    public PieChart pieChart;
    public JFXButton employeeReport;
    public JFXButton supplierReport;
    public JFXButton managerReport;
    public JFXButton vehicleReport;
    public JFXButton itemReport;
    public JFXButton paymentReport;
    public JFXButton orderReport;
    public JFXButton attendanceReport;
    public JFXButton btnItemMoveChart;

    public void initialize(){

        setLineChart();
        setPieChart();
        setGrnLineChart();

    }

    private void setPieChart(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Supervisor", 5),
                        new PieChart.Data("Carpenter", 12),
                        new PieChart.Data("Mason", 12),
                        new PieChart.Data("Worker", 22),
                        new PieChart.Data("Plumber", 12),
                        new PieChart.Data("Hand helper", 24),
                        new PieChart.Data("Driver", 8),
                        new PieChart.Data("Stock keeper", 5)

                );
        pieChart.setData(pieChartData);
        pieChart.setLegendSide(Side.LEFT);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });
        }
    }

    private void setLineChart() {
        XYChart.Series s = new XYChart.Series<>();
        s.setName("Attendance");
        s.getData().add(new XYChart.Data<>("1", 10));
        s.getData().add(new XYChart.Data<>("2", 12));
        s.getData().add(new XYChart.Data<>("3", 16));
        s.getData().add(new XYChart.Data<>("4", 20));
        s.getData().add(new XYChart.Data<>("5", 30));
        s.getData().add(new XYChart.Data<>("6", 24));

        attendanceLineChart.getData().addAll(s);
    }

    private void setGrnLineChart() {
        XYChart.Series s = new XYChart.Series<>();
        s.setName("GRN Details");
        s.getData().add(new XYChart.Data<>("1", 14));
        s.getData().add(new XYChart.Data<>("2", 16));
        s.getData().add(new XYChart.Data<>("3", 20));
        s.getData().add(new XYChart.Data<>("4", 25));
        s.getData().add(new XYChart.Data<>("5", 36));
        s.getData().add(new XYChart.Data<>("6", 27));

        attendanceLineChart.getData().addAll(s);
    }

    public void goToEmployeeReport(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/EmployeeSqlReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void goToSupplierReport(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/SupplierSqlReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void goToManagerReport(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/ManagerSqlReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void goToVehicleReport(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/VehicleSqlReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void goToItemReport(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/ItemSqlReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void goToPaymentReport(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/PaymentSqlReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void goToOrderReport(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/OrderSqlReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void goToAttendanceReport(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/AttendanceSqlReport.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void itemMoveChartOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/MostBuyItems.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }
}
