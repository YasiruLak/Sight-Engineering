package model;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String supplierId;
    private String date;
    private String time;
    private double cost;
    private ArrayList<ItemDetails> items;

    public Order() {
    }

    public Order(String orderId, String supplierId, String date, String time, double cost) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public Order(String orderId, String supplierId, String date, String time, double cost, ArrayList<ItemDetails> items) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetails> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                ", items=" + items +
                '}';
    }
}
