package view.tm;

import javafx.scene.control.Button;

public class ItemDetailTM {

    private String id;
    private String name;
    private String size;
    private int quantity;
    private int receiveQty;

    public ItemDetailTM() {
    }

    public ItemDetailTM(String id, String name, String size, int quantity, int receiveQty) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.quantity = quantity;
        this.receiveQty = receiveQty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(int receiveQty) {
        this.receiveQty = receiveQty;
    }

    @Override
    public String toString() {
        return "ItemDetailTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", receiveQty=" + receiveQty +
                '}';
    }
}
