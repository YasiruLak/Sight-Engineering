package model;

import java.util.List;

public class ItemDetail{
    private String itemCode;
    private String itemName;
    private String attendId;
    private int qty;
    private String status;
    private int receiveQty;

    public ItemDetail() {
    }

    public ItemDetail(String itemCode, String itemName, String attendId, int qty, String status, int receiveQty) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.attendId = attendId;
        this.qty = qty;
        this.status = status;
        this.receiveQty = receiveQty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAttendId() {
        return attendId;
    }

    public void setAttendId(String attendId) {
        this.attendId = attendId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(int receiveQty) {
        this.receiveQty = receiveQty;
    }

    @Override
    public String toString() {
        return "ItemDetail{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", attendId='" + attendId + '\'' +
                ", qty=" + qty +
                ", status='" + status + '\'' +
                ", receiveQty=" + receiveQty +
                '}';
    }
}
