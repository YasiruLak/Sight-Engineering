package model;

import java.sql.Time;

public class AttendanceView {

    private String attendanceId;
    private String employeeId;
    private String employeName;
    private String employeeType;
    private Time attendTime;

    public AttendanceView() {
    }

    public AttendanceView(String attendanceId, String employeeId, String employeName, String employeeType, Time attendTime) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.employeName = employeName;
        this.employeeType = employeeType;
        this.attendTime = attendTime;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeName() {
        return employeName;
    }

    public void setEmployeName(String employeName) {
        this.employeName = employeName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Time getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Time attendTime) {
        this.attendTime = attendTime;
    }

    @Override
    public String toString() {
        return "AttendanceView{" +
                "attendanceId='" + attendanceId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeName='" + employeName + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", attendTime=" + attendTime +
                '}';
    }
}
