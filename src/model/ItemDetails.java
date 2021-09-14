package model;

public class ItemDetails {
    private String itemCode;
    private double unitPrice;
    int qtyForBuy;

    public ItemDetails() {
    }

    public ItemDetails(String itemCode, double unitPrice, int qtyForBuy) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qtyForBuy = qtyForBuy;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyForBuy() {
        return qtyForBuy;
    }

    public void setQtyForBuy(int qtyForBuy) {
        this.qtyForBuy = qtyForBuy;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyForBuy=" + qtyForBuy +
                '}';
    }
}
