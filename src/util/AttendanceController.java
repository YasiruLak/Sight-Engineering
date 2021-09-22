package util;

import db.DbConnection;
import enums.AttendType;
import model.Attendance;
import model.ItemDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceController {

    public String getAttendanceId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM attendance ORDER BY attendId DESC LIMIT 1").executeQuery();
        if ( resultSet.next() ){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId=tempId+1;
            if(tempId<9){
                return "A-000"+tempId;
            }else if(tempId<999){
                return "A-00"+tempId;
            }else if(tempId<99){
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

    public boolean saveItemDetail(ItemDetail itemDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSER INTO item_detail VALUES(?,?,?,?,?)");
        statement.setObject(1,itemDetail.getItemCode());
        statement.setObject(2,itemDetail.getAttendId());
        statement.setObject(3,itemDetail.getQty());
        statement.setObject(4,itemDetail.getStatus());
        statement.setObject(5,itemDetail.getReceiveQty());
        return  statement.executeUpdate()>0;

    }

}
