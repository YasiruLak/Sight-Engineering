package util;

import db.DbConnection;
import enums.AttendType;
import model.Attendance;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceController {

    public Attendance getAttendance(String id) throws SQLException, ClassNotFoundException {
        AttendType in = AttendType.IN;
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM attendance WHERE eId=? status=ATTEND");
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Attendance(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }else {
            return null;
        }
    }

}
