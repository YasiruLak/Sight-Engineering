package view.tm;

public class SupplierTm {
    private String id;
    private String name;
    private String address;
    private String mobil;
    private String email;

    public SupplierTm() {
    }

    public SupplierTm(String id, String name, String address, String mobil, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobil = mobil;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SupplierTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobil='" + mobil + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
