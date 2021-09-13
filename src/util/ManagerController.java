package util;

import db.DbConnection;
import model.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ManagerController {

    public List<String> getManagerNic() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM manager").executeQuery();
        List<String> nic = new ArrayList<>();
        while (rst.next()){
            nic.add(
                    rst.getString(1)
            );
        }
        return nic;
    }

    public boolean saveManager(Manager manager) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO manager VALUES (?,?,?,?,?,?,?)");
        statement.setObject(1,manager.getNicNo());
        statement.setObject(2,manager.getName());
        statement.setObject(3,manager.getStatus());
        statement.setObject(4,manager.getAge());
        statement.setObject(5,manager.getAddress());
        statement.setObject(6,manager.getMobile());
        statement.setObject(7,manager.getEmail());

        return statement.executeUpdate()>0;
    }
    public ArrayList<Manager> getAllManager() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM manager");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Manager> managers = new ArrayList<>();
        while (resultSet.next()){
            managers.add(new Manager(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)

            ));
        }
        return managers;
    }
    public Manager getManager(String nic) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM manager WHERE nic=?");
        stm.setObject(1, nic);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Manager(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        } else {
            return null;
        }
    }

    public boolean updateManager(Manager manager) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE manager SET name=?, status=?, age=?, address=?, mobile=?, email=? WHERE nic=? "
        );
        statement.setObject(1,manager.getName());
        statement.setObject(2,manager.getStatus());
        statement.setObject(3,manager.getAge());
        statement.setObject(4,manager.getAddress());
        statement.setObject(5,manager.getMobile());
        statement.setObject(6,manager.getEmail());
        statement.setObject(7,manager.getNicNo());

        return statement.executeUpdate()>0;
    }

    public boolean deleteManager(String managerNic) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM manager WHERE nic=" +
                "'"+managerNic+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }
}
