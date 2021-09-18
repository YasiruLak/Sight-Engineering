package view.tm;

public class ItemViewTm {

    private String itemCode;
    private String itemName;
    private String size;
    private String  quantityOnHand;

    public ItemViewTm() {
    }

    public ItemViewTm(String itemCode, String itemName, String size, String quantityOnHand) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.size = size;
        this.quantityOnHand = quantityOnHand;
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

    @Override
    public String toString() {
        return "ItemViewTm{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", size='" + size + '\'' +
                ", quantityOnHand='" + quantityOnHand + '\'' +
                '}';
    }
}
