package view.tm;

public class PaymentTm {

    private String paymentId;
    private String orderId;
    private String supplierId;
    private String orderDate;
    private String date;
    private String time;
    private double amount;
    private String payMethod;
    private String invoiceNo;

    public PaymentTm() {
    }

    public PaymentTm(String paymentId, String orderId, String supplierId, String orderDate,
                     String date, String time, double amount, String payMethod, String invoiceNo) {
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
        return "PaymentTm{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", amount=" + amount +
                ", payMethod='" + payMethod + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                '}';
    }
}
