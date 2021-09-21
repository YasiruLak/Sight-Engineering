package model;

import java.sql.Date;
import java.sql.Time;

public class Attendance {

    private String attendId;
    private String eId;
    private Date attendDate;
    private Time attendTime;
    private Date returnDate;
    private Time returnTime;
    private String status;

    public Attendance(String attendId) {
        this.attendId = attendId;
    }

    public Attendance(String attendId, String eId, Date attendDate, Time attendTime, Date returnDate, Time returnTime, String status) {
        this.attendId = attendId;
        this.eId = eId;
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

    public Date getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Date attendDate) {
        this.attendDate = attendDate;
    }

    public Time getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Time attendTime) {
        this.attendTime = attendTime;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Time getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Time returnTime) {
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
                ", attendDate=" + attendDate +
                ", attendTime=" + attendTime +
                ", returnDate=" + returnDate +
                ", returnTime=" + returnTime +
                ", status='" + status + '\'' +
                '}';
    }
}
