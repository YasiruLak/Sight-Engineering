package view.tm;

public class ItemTm {
    private String id;
    private String name;
    private String description;
    private String size;
    private String qtyOnHand;

    public ItemTm() {
    }

    public ItemTm(String id, String name, String description, String size, String qtyOnHand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.qtyOnHand = qtyOnHand;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                '}';
    }
}
