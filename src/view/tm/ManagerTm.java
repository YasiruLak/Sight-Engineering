package view.tm;

public class ManagerTm {
    private String nicNo;
    private String name;
    private String status;
    private String age;
    private String address;
    private String mobile;
    private String email;

    public ManagerTm() {
    }

    public ManagerTm(String nicNo, String name, String status, String age, String address, String mobile, String email) {
        this.nicNo = nicNo;
        this.name = name;
        this.status = status;
        this.age = age;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ManagerTm{" +
                "nicNo='" + nicNo + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
