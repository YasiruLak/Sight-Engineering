package model;

public class Material {
    private String iId;
    private String eId;
    private String aId;
    private String qty;
    private String status;
    private String receiveQty;

    public Material() {
    }

    public Material(String iId, String eId, String aId, String qty, String status, String receiveQty) {
        this.iId = iId;
        this.eId = eId;
        this.aId = aId;
        this.qty = qty;
        this.status = status;
        this.receiveQty = receiveQty;
    }

    public String getiId() {
        return iId;
    }

    public void setiId(String iId) {
        this.iId = iId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(String receiveQty) {
        this.receiveQty = receiveQty;
    }

    @Override
    public String toString() {
        return "Material{" +
                "iId='" + iId + '\'' +
                ", eId='" + eId + '\'' +
                ", aId='" + aId + '\'' +
                ", qty='" + qty + '\'' +
                ", status='" + status + '\'' +
                ", receiveQty='" + receiveQty + '\'' +
                '}';
    }
}
