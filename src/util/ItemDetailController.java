package util;

import db.DbConnection;
import enums.ItemStatus;
import model.ItemDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDetailController {
    public List<ItemDetail> getItemDetail(String attendId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM item_detail WHERE aId=? AND status=? ");
        statement.setObject(1, attendId);
        statement.setObject(2, ItemStatus.PENDING.toString());
        ResultSet resultSet = statement.executeQuery();
        List<ItemDetail> itemDetailList = new ArrayList<>();
        while (resultSet.next()) {
            ItemDetail itemDetail = new ItemDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getInt(6)
            );
            itemDetailList.add(itemDetail);
        }
        return itemDetailList;
    }

    public boolean saveItemDetail(ItemDetail itemDetail) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO item_detail VALUES (?,?,?,?,?,?)");
        statement.setObject(1,itemDetail.getItemCode());
        statement.setObject(2,itemDetail.getItemName());
        statement.setObject(3,itemDetail.getAttendId());
        statement.setObject(4,itemDetail.getQty());
        statement.setObject(5,itemDetail.getStatus());
        statement.setObject(6,itemDetail.getReceiveQty());

        return statement.executeUpdate()>0;
    }

    public boolean updateItemDetail(ItemDetail itemDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE item_detail SET name=?, qty=?, status=?, receiveQty=? WHERE iId=? AND aId=?");
        statement.setObject(1, itemDetail.getItemName());
        statement.setObject(2, itemDetail.getQty());
        statement.setObject(3, itemDetail.getStatus());
        statement.setObject(4, itemDetail.getReceiveQty());
        statement.setObject(5, itemDetail.getItemCode());
        statement.setObject(6, itemDetail.getAttendId());
        return statement.executeUpdate()>0;
    }
}
