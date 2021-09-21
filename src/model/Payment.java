package model;

import java.sql.Date;
import java.sql.Time;

public class Payment {
    private String paymentId;
    private String orderId;
    private String supplierId;
    private Date orderDate;
    private Date date;
    private Time time;
    private double amount;
    private String payMethod;
    private String invoiceNo;

    public Payment() {
    }

    public Payment(String paymentId, String orderId, String supplierId, Date orderDate,
                   Date date, Time time, double amount, String payMethod, String invoiceNo) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.payMethod = payMethod;
        this.invoiceNo = invoiceNo;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", orderDate=" + orderDate +
                ", date=" + date +
                ", time=" + time +
                ", amount=" + amount +
                ", payMethod='" + payMethod + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                '}';
    }
}
