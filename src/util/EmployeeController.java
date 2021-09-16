package util;

import db.DbConnection;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeController{

    int size = 0;

    public boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?)");
        statement.setObject(1,employee.getId());
        statement.setObject(2,employee.getName());
        statement.setObject(3,employee.getType());
        statement.setObject(4,employee.getAge());
        statement.setObject(5,employee.getAddress());
        statement.setObject(6,employee.getCity());
        statement.setObject(7,employee.getProvince());
        statement.setObject(8,employee.getContact());
        statement.setObject(9,employee.getDailySalary());
        return statement.executeUpdate()>0;
    }


    public Employee getEmployee(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE empId=?");
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }else {
            return null;
        }
    }

    public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE employee SET name=?, type =?, age=?, address=?, city=?, province=?, contact=?, dailySalary=? WHERE empId=? "
        );
        statement.setObject(1,employee.getName());
        statement.setObject(2,employee.getType());
        statement.setObject(3,employee.getAge());
        statement.setObject(4,employee.getAddress());
        statement.setObject(5,employee.getCity());
        statement.setObject(6,employee.getProvince());
        statement.setObject(7,employee.getContact());
        statement.setObject(8,employee.getDailySalary());
        statement.setObject(9,employee.getId());
        return statement.executeUpdate()>0;
    }


    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM employee WHERE empId=" +
                "'"+employeeId+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }


    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM employee");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Employee> employees = new ArrayList<>();
        while (resultSet.next()){
            employees.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return employees;
    }

    public int employeeCount() throws SQLException, ClassNotFoundException {
        int numberRow = 0;
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT COUNT (*) FROM employee");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            numberRow = resultSet.getInt("count(*)");
        }
        return numberRow;
    }
}
