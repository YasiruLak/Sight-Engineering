package util;

import db.DbConnection;
import model.Login;

import java.sql.PreparedStatement;
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
}
