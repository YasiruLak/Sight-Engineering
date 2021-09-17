package view.tm;


import javafx.scene.control.Button;

public class ItemViewTm {

    private String itemCode;
    private String itemName;
    private String size;
    private String  quantityOnHand;
    private Button button;

    public ItemViewTm() {
    }

    public ItemViewTm(String itemCode, String itemName, String size, String quantityOnHand, Button button) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.size = size;
        this.quantityOnHand = quantityOnHand;
        this.button = button;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(String quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "ItemViewTm{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", size='" + size + '\'' +
                ", quantityOnHand='" + quantityOnHand + '\'' +
                ", button=" + button +
                '}';
    }
}
