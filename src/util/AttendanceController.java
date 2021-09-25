package util;

import db.DbConnection;
import enums.AttendType;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceController {

    public String getAttendanceId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM attendance ORDER BY attendId DESC LIMIT 1").executeQuery();
        if ( resultSet.next() ){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId=tempId+1;
            if(tempId<=9){
                return "A-000"+tempId;
            }else if(tempId<=99){
                return "A-00"+tempId;
            }else if(tempId<=999){
                return "A-0"+tempId;
            }else{
                return "A-" + tempId;
            }
        }else{
            return "A-0001";
        }
    }
    public Attendance getAttendance(String attendId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM attendance WHERE eId=? AND status=?");
        statement.setObject(1, attendId);
        statement.setObject(2, AttendType.ATTEND.toString());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Attendance(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getTime(4),
                    resultSet.getDate(5),
                    resultSet.getTime(6),
                    resultSet.getString(7)
            );
        }else {
            return null;
        }
    }

    public boolean saveAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {

        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("INSERT INTO attendance VALUES (?,?,?,?,?,?,?)");
        statement.setObject(1, attendance.getAttendId());
        statement.setObject(2, attendance.geteId());
        statement.setObject(3, attendance.getAttendDate());
        statement.setObject(4, attendance.getAttendTime());
        statement.setObject(5, attendance.getReturnDate());
        statement.setObject(6, attendance.getReturnTime());
        statement.setObject(7, attendance.getStatus());
        return statement.executeUpdate()>0;
    }

    public boolean updateAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE attendance SET eId=?, inDate=?, inTime=?, outDate=?, outTime=?, status=? WHERE attendId=?");
        statement.setObject(1, attendance.geteId());
        statement.setObject(2, attendance.getAttendDate());
        statement.setObject(3, attendance.getAttendTime());
        statement.setObject(4, attendance.getReturnDate());
        statement.setObject(5, attendance.getReturnTime());
        statement.setObject(6, attendance.getStatus());
        statement.setObject(7, attendance.getAttendId());
        return statement.executeUpdate()>0;

    }

    public ArrayList<AttendanceView> getAllAttendance() throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceView> list = new ArrayList();
        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement
                        ("SELECT a.attendId, e.empId, e.name, e.type, a.inTime FROM employee e JOIN attendance a ON a.eId=e.empId")
                .executeQuery();

        while (resultSet.next()) {
            list.add(
                    new AttendanceView(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getTime(5)
                    )
            );
        }
        return list;
    }

    public ArrayList<ItemDetailView> getAllItemDetail(String attendanceId) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDetailView> details = new ArrayList<>();
        ResultSet resultSet = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT iId, name, qty FROM item_detail WHERE aId='"
                        + attendanceId + "'").executeQuery();
        while (resultSet.next()) {
            details.add(
                    new ItemDetailView(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3)

                    )
            );
        }
        return details;
    }

}
