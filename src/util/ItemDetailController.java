package util;

import db.DbConnection;
import javafx.collections.ObservableList;
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
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM item_detail WHERE aId=? ");
        statement.setObject(1, attendId);
        ResultSet resultSet = statement.executeQuery();
        List<ItemDetail> itemDetailList = new ArrayList<>();
        while (resultSet.next()) {
            ItemDetail itemDetail = new ItemDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
            itemDetailList.add(itemDetail);
        }
        return itemDetailList;
    }
}
