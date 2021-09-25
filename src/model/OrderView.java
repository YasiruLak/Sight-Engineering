package model;

import java.sql.Date;
import java.sql.Time;

public class OrderView {

    private String supplierId;
    private String supplierName;
    private String orderId;
    private Date date;
    private Time time;
    private double totalCost;

    public OrderView() {
    }

    public OrderView(String supplierId, String supplierName, String orderId, Date date, Time time, double totalCost) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.totalCost = totalCost;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "OrderView{" +
                "supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", totalCost=" + totalCost +
                '}';
    }
}
