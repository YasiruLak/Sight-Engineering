package model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Order {
    private String orderId;
    private String supplierId;
    private Date date;
    private Time time;
    private double cost;
    private ArrayList<OrderDetail> items;


    public Order(String orderId, String supplierId, Date date, Time time, double cost) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public Order(String orderId, String supplierId, Date date, Time time, double cost, ArrayList<OrderDetail> items) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<OrderDetail> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetail> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", cost=" + cost +
                ", items=" + items +
                '}';
    }
}
