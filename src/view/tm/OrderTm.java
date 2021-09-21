package view.tm;

import java.sql.Date;
import java.sql.Time;

public class OrderTm {

    private String orderId;
    private String supplierId;
    private Date date;
    private Time time;
    private double cost;

    public OrderTm() {
    }

    public OrderTm(String orderId, String supplierId, Date date, Time time, double cost) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.date = date;
        this.time = time;
        this.cost = cost;
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

    @Override
    public String toString() {
        return "OrderTm{" +
                "orderId='" + orderId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", cost=" + cost +
                '}';
    }
}
