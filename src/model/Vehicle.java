package model;


public class Vehicle {
    private String vehicleNo;
    private String description;
    private String type;


    public Vehicle(String vehicleNo, String description, String type) {
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
        return "Vehicle{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
