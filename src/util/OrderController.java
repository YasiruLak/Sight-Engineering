package util;

import db.DbConnection;
import model.ItemDetails;
import model.Order;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM `order` ORDER BY orderId DESC LIMIT 1").executeQuery();
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

    public boolean saveOrder(Order order) throws SQLException, ClassNotFoundException {

        Connection connection = null;

        try{

        connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement("INSERT INTO `order` VALUES (?,?,?,?,?)");



        statement.setObject(1,order.getOrderId());
        statement.setObject(2,order.getSupplierId());
        statement.setObject(3,order.getDate());
        statement.setObject(4,order.getTime());
        statement.setObject(5,order.getCost());

            if (statement.executeUpdate() > 0){
                if (saveOrderDetail(order.getOrderId(), order.getItems())){
                    connection.commit();
                    return true;
                }else{
                    connection.rollback();
                    return false;
                }
            }else{
                connection.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }finally {

            try {

                connection.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    private boolean saveOrderDetail(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        for (ItemDetails itemDetails: items
        ){
            PreparedStatement statement = DbConnection.getInstance().getConnection().
                    prepareStatement("INSERT INTO `order detail` VALUES(?,?,?,?)");
            statement.setObject(1,itemDetails.getItemCode());
            statement.setObject(2,orderId);
            statement.setObject(3,itemDetails.getQtyForBuy());
            statement.setObject(4,itemDetails.getUnitPrice());

            if(statement.executeUpdate()>0){
                if(updateQty(itemDetails.getItemCode(), itemDetails.getQtyForBuy())){

                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {

        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE item SET qtyOnHand=(qtyOnHand+" + qty + ") WHERE itemCode='" + itemCode + "'");
        return  statement.executeUpdate()>0;

    }

    public ArrayList<Order> getAllOrder() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM `order`");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Order> orders = new ArrayList<>();
        while (resultSet.next()){
            orders.add(new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)

            ));
        }
        return orders;
    }
}
