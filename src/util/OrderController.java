package util;

import db.DbConnection;
import model.OrderDetail;
import model.Order;
import model.OrderView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM orders ORDER BY orderId DESC LIMIT 1").executeQuery();
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

        PreparedStatement statement = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?)");
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

    private boolean saveOrderDetail(String orderId, ArrayList<OrderDetail> items) throws SQLException, ClassNotFoundException {
        for ( OrderDetail orderDetail : items
        ){
            PreparedStatement statement = DbConnection.getInstance().getConnection().
                    prepareStatement("INSERT INTO order_detail VALUES(?,?,?,?)");
            statement.setObject(1, orderDetail.getItemCode());
            statement.setObject(2,orderId);
            statement.setObject(3, orderDetail.getQtyForBuy());
            statement.setObject(4, orderDetail.getUnitPrice());

            if(statement.executeUpdate()>0){
                if(updateQty(orderDetail.getItemCode(), orderDetail.getQtyForBuy())){

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

    public Order getOrder(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM orders WHERE orderId=?");
        statement.setObject(1, orderId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getTime(4),
                    resultSet.getDouble(5)
            );
        }else{
            return null;
        }
    }

    public int orderCount() throws SQLException, ClassNotFoundException {
        int numberRow = 0;
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT COUNT(*) FROM orders");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            numberRow = resultSet.getInt("count(*)");
        }
        return numberRow;
    }

    public ArrayList<OrderView> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<OrderView> list = new ArrayList();
        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement
                        ("SELECT s.id, s.name, o.orderId, o.orderDate, o.time, o.cost FROM supplier s JOIN orders o ON o.sid=s.id")
                .executeQuery();
        while (resultSet.next()) {
            list.add(
                    new OrderView(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4),
                            resultSet.getTime(5),
                            resultSet.getDouble(6)
                            )
            );
        }
        return list;
    }

    public ArrayList<OrderDetail> getAllOrderDetails(String orderId) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> details = new ArrayList<>();
        ResultSet resultSet = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT iCode, qty, price FROM order_detail WHERE oId='"
                        + orderId + "'").executeQuery();
        while (resultSet.next()) {
            details.add(
                    new OrderDetail(
                            resultSet.getString(1),
                            resultSet.getInt(2),
                            resultSet.getDouble(3)
                    )
            );
        }
        return details;
    }
}
