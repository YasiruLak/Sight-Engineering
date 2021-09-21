package util;

import db.DbConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController {

    public List<String> getItemCodes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM item").executeQuery();
        List<String> code = new ArrayList<>();
        while (resultSet.next()){
            code.add(
                    resultSet.getString(1)
            );
        }
        return code;
    }
    public boolean saveItem(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO item VALUES (?,?,?,?,?)");
        statement.setObject(1,item.getId());
        statement.setObject(2,item.getName());
        statement.setObject(3,item.getDescription());
        statement.setObject(4,item.getSize());
        statement.setObject(5,item.getQtyOnHand());
        return statement.executeUpdate()>0;
    }


    public Item getItem(String itemCode) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM item WHERE itemCode=?");
        statement.setObject(1,itemCode);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
        }else{
            return null;

        }
    }

    public boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE item SET name=?, description=?, size=?, qtyOnHand=? WHERE itemCode=?");
        statement.setObject(1,item.getName());
        statement.setObject(2,item.getDescription());
        statement.setObject(3,item.getSize());
        statement.setObject(4,item.getQtyOnHand());
        statement.setObject(5,item.getId());
        return statement.executeUpdate()>0;
    }

    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM item WHERE itemCode=" +
                "'"+itemCode+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM item");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (resultSet.next()){
            items.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));
        }
        return items;
    }

    public int itemCount() throws SQLException, ClassNotFoundException {
        int numberRow = 0;
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT COUNT(*) FROM item");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            numberRow = resultSet.getInt("count(*)");
        }
        return numberRow;
    }
}
