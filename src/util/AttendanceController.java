package util;

import db.DbConnection;
import enums.AttendType;
import model.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceController {

    public String getAttendanceId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM attendance ORDER BY orderId DESC LIMIT 1").executeQuery();
        if ( resultSet.next() ){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId=tempId+1;
            if(tempId<9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }
        }else{
            return "O-001";
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

}
