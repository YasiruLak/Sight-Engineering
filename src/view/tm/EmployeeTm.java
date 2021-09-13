package view.tm;

public class EmployeeTm {
    private String id;
    private String name;
    private String type;
    private String age;
    private String address;
    private String city;
    private String province;
    private String contact;
    private String dailySalary;

    public EmployeeTm() {
    }

    public EmployeeTm(String id, String name, String type, String age, String address, String city, String province,
                      String contact, String dailySalary) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.address = address;
        this.city = city;
        this.province = province;
        this.contact = contact;
        this.dailySalary = dailySalary;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(String dailySalary) {
        this.dailySalary = dailySalary;
    }

    @Override
    public String toString() {
        return "EmployeeTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", contact='" + contact + '\'' +
                ", dailySalary='" + dailySalary + '\'' +
                '}';
    }
}
