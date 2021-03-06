package model;

public class OrderDetail {
    private String itemCode;
    private int qtyForBuy;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(String itemCode, int qtyForBuy, double unitPrice) {
        this.itemCode = itemCode;
        this.qtyForBuy = qtyForBuy;
        this.unitPrice = unitPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQtyForBuy() {
        return qtyForBuy;
    }

    public void setQtyForBuy(int qtyForBuy) {
        this.qtyForBuy = qtyForBuy;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "itemCode='" + itemCode + '\'' +
                ", qtyForBuy=" + qtyForBuy +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
