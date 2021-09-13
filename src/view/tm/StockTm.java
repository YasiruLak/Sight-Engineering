package view.tm;

import java.awt.*;

public class StockTm {
    private String itemCode;
    private String name;
    private int qty;
    private double total;
    private Button update;
    private Button delete;

    public StockTm() {
    }

    public StockTm(String itemCode, String name, int qty, double total, Button update, Button delete) {
        this.itemCode = itemCode;
        this.name = name;
        this.qty = qty;
        this.total = total;
        this.update = update;
        this.delete = delete;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "StockTm{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", total=" + total +
                ", update=" + update +
                ", delete=" + delete +
                '}';
    }
}
