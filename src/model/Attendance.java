package model;

public class Attendance {

    private String attendId;
    private String eId;
    private String name;
    private String catogary;
    private String attendDate;
    private String attendTime;
    private String returnDate;
    private String returnTime;
    private String status;

    public Attendance() {
    }

    public Attendance(String attendId, String eId, String name, String catogary, String attendDate,
                      String attendTime, String returnDate, String returnTime, String status) {
        this.attendId = attendId;
        this.eId = eId;
        this.name = name;
        this.catogary = catogary;
        this.attendDate = attendDate;
        this.attendTime = attendTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.status = status;
    }

    public String getAttendId() {
        return attendId;
    }

    public void setAttendId(String attendId) {
        this.attendId = attendId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatogary() {
        return catogary;
    }

    public void setCatogary(String catogary) {
        this.catogary = catogary;
    }

    public String getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(String attendDate) {
        this.attendDate = attendDate;
    }

    public String getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(String attendTime) {
        this.attendTime = attendTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendId='" + attendId + '\'' +
                ", eId='" + eId + '\'' +
                ", name='" + name + '\'' +
                ", catogary='" + catogary + '\'' +
                ", attendDate='" + attendDate + '\'' +
                ", attendTime='" + attendTime + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
