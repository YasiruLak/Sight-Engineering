package view.tm;

import javafx.scene.control.Button;

public class ItemDetailTM {

    private String id;
    private String name;
    private String size;
    private String  quantity;
    private Button action;
    private String receiveQty;

    public ItemDetailTM() {
    }

    public ItemDetailTM(String id, String name, String size, String quantity, Button action, String receiveQty) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.quantity = quantity;
        this.action = action;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    public String getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(String receiveQty) {
        this.receiveQty = receiveQty;
    }

    @Override
    public String toString() {
        return "ItemDetailTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", quantity='" + quantity + '\'' +
                ", action=" + action +
                ", receiveQty='" + receiveQty + '\'' +
                '}';
    }
}
