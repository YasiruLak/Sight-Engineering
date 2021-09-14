package view.tm;

public class OrderTm {

    private String orderId;
    private String supplierId;
    private String date;
    private String time;
    private double cost;

    public OrderTm() {
    }

    public OrderTm(String orderId, String supplierId, String date, String time, double cost) {
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

    @Override
    public String toString() {
        return "OrderTm{" +
                "orderId='" + orderId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                '}';
    }
}
