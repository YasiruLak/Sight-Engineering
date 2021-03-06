package util;

import db.DbConnection;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentController {

    public String getPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM payment ORDER BY paymentId DESC LIMIT 1").executeQuery();
        if ( resultSet.next() ){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId=tempId+1;
            if(tempId<9){
                return "P-00"+tempId;
            }else if(tempId<99){
                return "P-0"+tempId;
            }else{
                return "P-"+tempId;
            }
        }else{
            return "P-001";
        }
    }

    public boolean savePayment(Payment payment) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO payment VALUES (?,?,?,?,?,?,?,?,?)");
        statement.setObject(1,payment.getPaymentId());
        statement.setObject(2,payment.getOrderId());
        statement.setObject(3,payment.getSupplierId());
        statement.setObject(4,payment.getOrderDate());
        statement.setObject(5,payment.getDate());
        statement.setObject(6,payment.getTime());
        statement.setObject(7,payment.getAmount());
        statement.setObject(8,payment.getPayMethod());
        statement.setObject(9,payment.getInvoiceNo());
        return statement.executeUpdate()>0;
    }

    public ArrayList<Payment> getAllPayment() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM payment");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Payment> payments = new ArrayList<>();
        while (resultSet.next()){
            payments.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getDate(5),
                    resultSet.getTime(6),
                    resultSet.getDouble(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return payments;
    }

    public Payment getPayment(String paymentId) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM payment WHERE paymentId=?");
        statement.setObject(1, paymentId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getDate(5),
                    resultSet.getTime(6),
                    resultSet.getDouble(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }else{
            return null;
        }
    }

    public int paymentCount() throws SQLException, ClassNotFoundException {
        int numberRow = 0;
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT COUNT(*) FROM payment");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            numberRow = resultSet.getInt("count(*)");
        }
        return numberRow;
    }
}
