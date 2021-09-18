package util;

import db.DbConnection;
import enums.AttendType;
import model.Attendance;
import model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialController {
    public Material getMaterial(String id) throws SQLException, ClassNotFoundException {
        AttendType in = AttendType.IN;
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM material WHERE aId=? ");
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Material(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }else {
            return null;
        }
    }
}
