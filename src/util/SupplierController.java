package util;

import db.DbConnection;
import model.Employee;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {

    public List<String> getSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM supplier").executeQuery();
        List<String> id = new ArrayList<>();
        while (rst.next()){
            id.add(
                    rst.getString(1)
            );
        }
        return id;
    }

    public boolean saveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO supplier VALUES (?,?,?,?,?)");
        statement.setObject(1,supplier.getId());
        statement.setObject(2,supplier.getName());
        statement.setObject(3,supplier.getAddress());
        statement.setObject(4,supplier.getMobil());
        statement.setObject(5,supplier.getEmail());

        return statement.executeUpdate()>0;
    }

    public ArrayList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM supplier");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()){
            suppliers.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return suppliers;
    }

    public Supplier getSupplier(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM supplier WHERE id=?");
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        } else {
            return null;
        }
    }

    public boolean updateSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE supplier SET name=?, address=?, mobile=?, email=? WHERE id=? "
        );
        statement.setObject(1,supplier.getName());
        statement.setObject(2,supplier.getAddress());
        statement.setObject(3,supplier.getMobil());
        statement.setObject(4,supplier.getEmail());
        statement.setObject(5,supplier.getId());

        return statement.executeUpdate()>0;
    }

    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM supplier WHERE id=" +
                "'"+supplierId+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    public int supplierCount() throws SQLException, ClassNotFoundException {
        int rowCount = 0;
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT COUNT(*) FROM supplier");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            rowCount = resultSet.getInt("count(*)");
        }
        return rowCount;
    }
}
