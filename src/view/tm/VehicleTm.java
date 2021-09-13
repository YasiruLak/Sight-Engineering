package view.tm;

public class VehicleTm {
    private String vehicleNo;
    private String description;
    private String type;

    public VehicleTm() {
    }

    public VehicleTm(String vehicleNo, String description, String type) {
        this.vehicleNo = vehicleNo;
        this.description = description;
        this.type = type;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "VehicleTm{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
