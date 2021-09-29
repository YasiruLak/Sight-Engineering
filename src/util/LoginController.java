package util;

import db.DbConnection;
import model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public boolean saveManagerLogin(Login login) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO login VALUES (?,?,?,?)");
        statement.setObject(1, login.getNic());
        statement.setObject(2, login.getUserName());
        statement.setObject(3, login.getPassword());
        statement.setObject(4, login.getRole());
        return statement.executeUpdate()>0;
    }

    public Login getUser(String userName, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM login WHERE userName= ? AND password= ?");
        statement.setObject(1, userName);
        statement.setObject(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            return new Login(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }
}
