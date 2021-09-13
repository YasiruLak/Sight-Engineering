package util;

import db.DbConnection;
import model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleController{

    public boolean saveVehicle(Vehicle v) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO vehicle VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, v.getVehicleNo());
        stm.setObject(2, v.getDescription());
        stm.setObject(3, v.getType());
        return stm.executeUpdate() > 0;
    }


    public Vehicle getVehicle(String vehicleNo) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Vehicle WHERE vehicleNo=?");
        statement.setObject(1,vehicleNo);
        ResultSet rst = statement.executeQuery();
        if (rst.next()){
            return new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }else{
            return null;
        }
    }


    public boolean updateVehicle(Vehicle v) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE Vehicle SET description=?, type=? WHERE VehicleNo=?");
        stm.setObject(1,v.getDescription());
        stm.setObject(2,v.getType());
        stm.setObject(3,v.getVehicleNo());
        return stm.executeUpdate()>0;
    }


    public boolean deleteVehicle(String vehicleNo) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement
                ("DELETE FROM Vehicle WHERE vehicleNo='"+vehicleNo+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }


    public ArrayList<Vehicle> getAllVehicle() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Vehicle");
        ResultSet rst = statement.executeQuery();
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        while (rst.next()){
            vehicles.add(new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return vehicles;
    }


}
